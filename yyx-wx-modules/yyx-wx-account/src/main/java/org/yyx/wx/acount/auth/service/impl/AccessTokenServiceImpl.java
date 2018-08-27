package org.yyx.wx.acount.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.yyx.wx.acount.auth.config.WxPublicNumAuthConfig;
import org.yyx.wx.acount.auth.service.IAccessTokenService;
import org.yyx.wx.commons.vo.pubnum.reponse.BaseAccessToken;
import org.yyx.wx.commons.vo.pubnum.reponse.auth.AuthAccessToken;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static org.yyx.wx.commons.constant.CacheKeyConstant.ACCESS_TOKEN_NO_OPENID;
import static org.yyx.wx.commons.constant.CacheKeyConstant.AUTH_ACCESS_TOKEN;
import static org.yyx.wx.commons.constant.CacheKeyConstant.REFRESH_AUTH_ACCESS_TOKEN;


/**
 * AccessTokenService实现类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-10:58
 */
@Service
public class AccessTokenServiceImpl implements IAccessTokenService {

    /**
     * AccessTokenServiceImpl日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenServiceImpl.class);
    /**
     * 获取微信公众号配置对象
     */
    @Resource
    private WxPublicNumAuthConfig wxPublicNumAuthConfig;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void getAuthAccessToken(String code, String state) {
        String appID = wxPublicNumAuthConfig.getAppID();
        String appSecret = wxPublicNumAuthConfig.getAppSecret();
        // 从缓存中获取AccessToken
        AuthAccessToken cacheAuthToken = (AuthAccessToken) redisTemplate.opsForValue().get(AUTH_ACCESS_TOKEN);
        if (cacheAuthToken == null) {
            // 说明过期了。使用RefreshToken刷新
            String refreshToken = (String) redisTemplate.opsForValue().get(REFRESH_AUTH_ACCESS_TOKEN);
            if (StrUtil.hasEmpty(refreshToken)) {
                // RefreshToken过期，重新授权
                // https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
                String urlCodeToToken =
                        wxPublicNumAuthConfig.getUrlCodeToAuthAccessToken() + appID
                                + "&secret=" + appSecret
                                + "&code=" + code
                                + "&grant_type=authorization_code";
                String responseMessage = HttpUtil.get(urlCodeToToken);
                // 用于认证授权的AccessToken
                cacheAuthToken = JSONObject.parseObject(responseMessage, AuthAccessToken.class);
                LOGGER.info("[请求获取的AccessToken] {}", cacheAuthToken);
                cacheData(cacheAuthToken);
            } else {
                getAuthAccessToken(refreshToken);
            }
        }
    }

    /**
     * 缓存数据
     *
     * @param cacheAuthToken 待缓存数据
     */
    private void cacheData(AuthAccessToken cacheAuthToken) {
        // 缓存刷新Token
        redisTemplate.opsForValue().set(REFRESH_AUTH_ACCESS_TOKEN, cacheAuthToken.getRefresh_token());
        // 设置失效时长为29天
        redisTemplate.expire(REFRESH_AUTH_ACCESS_TOKEN, 29, TimeUnit.DAYS);
        // 缓存认证Token
        redisTemplate.opsForValue().set(AUTH_ACCESS_TOKEN, cacheAuthToken);
        // 设置失效时长
        redisTemplate.expire(AUTH_ACCESS_TOKEN, cacheAuthToken.getExpires_in(), TimeUnit.SECONDS);
    }

    /**
     * 获取认证授权Token的方法
     *
     * @param refreshToken 刷新Token
     * @return 认证授权Token
     */
    @Override
    public AuthAccessToken getAuthAccessToken(String refreshToken) {
        if (StrUtil.hasEmpty(refreshToken)) {
            return null;
        }
        AuthAccessToken cacheAuthToken = (AuthAccessToken) redisTemplate.opsForValue().get(AUTH_ACCESS_TOKEN);
        if (cacheAuthToken != null) {
            return cacheAuthToken;
        }
        String appID = wxPublicNumAuthConfig.getAppID();
        // https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
        String urlRefreshToken =
                wxPublicNumAuthConfig.getUrlRefreshToken() + appID
                        + "&grant_type=refresh_token&refresh_token="
                        + refreshToken;
        String responseMessage = HttpUtil.get(urlRefreshToken);
        // 用于认证授权的AccessToken
        cacheAuthToken = JSONObject.parseObject(responseMessage, AuthAccessToken.class);
        LOGGER.info("[刷新获取的AccessToken] {}", cacheAuthToken);
        cacheData(cacheAuthToken);
        return cacheAuthToken;
    }

    /**
     * 获取BaseAccessToken的方法
     *
     * @return 微信返回的AccessToken
     */
    @Override
    public BaseAccessToken getBaseAccessToken() {
        BaseAccessToken baseAccessToken = (BaseAccessToken) redisTemplate.opsForValue().get(ACCESS_TOKEN_NO_OPENID);
        if (baseAccessToken != null) {
            return baseAccessToken;
        }
        String appID = wxPublicNumAuthConfig.getAppID();
        String appSecret = wxPublicNumAuthConfig.getAppSecret();
        String urlGetAccessToken = wxPublicNumAuthConfig.getUrlGetAccessToken();
        // 获取accessToken
        String accessTokenJson = HttpUtil.get(urlGetAccessToken + appID + "&secret=" + appSecret);
        if (StrUtil.hasEmpty(accessTokenJson)) {
            return null;
        }
        // 将AccessToken转换成对象
        baseAccessToken = JSONObject.parseObject(accessTokenJson, BaseAccessToken.class);
        // 存入缓存中
        redisTemplate.opsForValue().set(ACCESS_TOKEN_NO_OPENID, baseAccessToken);
        // 设置超时时间
        redisTemplate.expire(ACCESS_TOKEN_NO_OPENID, baseAccessToken.getExpires_in(), TimeUnit.SECONDS);
        return baseAccessToken;
    }
}

package org.yyx.wx.acount.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yyx.wx.acount.auth.properties.CredentialProperties;
import org.yyx.wx.acount.auth.properties.Oauth2Properties;
import org.yyx.wx.acount.auth.service.IAccessTokenService;
import org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx;
import org.yyx.wx.commons.exception.token.AccessTokenException;
import org.yyx.wx.commons.util.CacheService;
import org.yyx.wx.commons.vo.pubnum.request.auth.AuthAccessTokenRequest;
import org.yyx.wx.commons.vo.pubnum.request.auth.BaseAccessTokenRequest;

import javax.annotation.Resource;

import static org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx.getCode;
import static org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx.isSuccess;
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
     * 与公众号对接时，用到的凭据配置类
     */
    @Resource
    private CredentialProperties credentialProperties;

    @Resource
    private Oauth2Properties oauth2Properties;
    /**
     * 缓存工具
     */
    @Resource
    private CacheService<String, Object> cacheService;

    /**
     * 缓存数据
     *
     * @param cacheAuthToken 待缓存数据
     */
    private void cacheData(AuthAccessTokenRequest cacheAuthToken, String userName) {
        String openid = cacheAuthToken.getOpenid();
        cacheService.cacheValue(userName, openid);
        int cacheTime = 29 * 24 * 3600;
        // 缓存刷新Token
        cacheService.cacheValue(openid + REFRESH_AUTH_ACCESS_TOKEN, cacheAuthToken.getRefresh_token(), cacheTime);
        // 缓存认证Token
        cacheService.cacheValue(openid + AUTH_ACCESS_TOKEN, cacheAuthToken, cacheAuthToken.getExpires_in());
    }

    /**
     * 获取认证授权的AccessToken
     *
     * @param code  微信返回code
     * @param state 业务参数
     */
    @Override
    public void getAuthAccessToken(String code, String state) {
        // 从缓存中获取OpenID
        String openId = (String) cacheService.getValue("user_" + state);
        // 从缓存中获取AuthAccessToken
        AuthAccessTokenRequest cacheAuthToken = (AuthAccessTokenRequest) cacheService.getValue(openId + AUTH_ACCESS_TOKEN);
        if (cacheAuthToken != null) {
            LOGGER.info("[缓存的AuthAccessToken] {}, [openID] {}", cacheAuthToken, cacheAuthToken.getOpenid());
        }
        LOGGER.info("[缓存过期，使用RefreshToken更新]");
        // 说明过期了。使用RefreshToken刷新
        String refreshToken = (String) cacheService.getValue(openId + REFRESH_AUTH_ACCESS_TOKEN);
        if (StrUtil.hasEmpty(refreshToken)) {
            // RefreshToken过期，重新授权
            LOGGER.info("[RefreshToken过期，重新授权]");
            // https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
            String urlCodeToToken = oauth2Properties.getWebViewAccessToken() + code;
            LOGGER.info("[授权URL] {}", urlCodeToToken);
            String responseMessage = HttpUtil.get(urlCodeToToken);
            // 用于认证授权的AccessToken
            cacheAuthToken = JSONObject.parseObject(responseMessage, AuthAccessTokenRequest.class);
            LOGGER.info("[授权获取的AuthAccessToken] {}, [openID] {}", cacheAuthToken, cacheAuthToken.getOpenid());
            cacheData(cacheAuthToken, state);
        } else {
            getAuthAccessToken(refreshToken, state);
        }

    }

    /**
     * 获取认证授权Token的方法
     *
     * @param refreshToken 刷新Token
     * @return 认证授权Token
     */
    @Override
    public AuthAccessTokenRequest getAuthAccessTokenByRefreshToken(String refreshToken, String userName) {
        if (StrUtil.hasEmpty(refreshToken)) {
            return null;
        }
        // 从缓存中获取OpenID
        String openId = (String) cacheService.getValue("user_" + userName);
        AuthAccessTokenRequest cacheAuthToken = (AuthAccessTokenRequest) cacheService.getValue(openId + AUTH_ACCESS_TOKEN);
        LOGGER.info("[缓存中的认证Token] {}", cacheAuthToken);
        if (cacheAuthToken != null) {
            return cacheAuthToken;
        }
        String urlRefreshToken =
                oauth2Properties.getRefreshToken() + refreshToken;
        String responseMessage = HttpUtil.get(urlRefreshToken);
        // 用于认证授权的AccessToken
        cacheAuthToken = JSONObject.parseObject(responseMessage, AuthAccessTokenRequest.class);
        LOGGER.info("[刷新获取的AccessToken] {}, [openID] {}", cacheAuthToken, cacheAuthToken.getOpenid());
        cacheData(cacheAuthToken, userName);
        return cacheAuthToken;
    }

    /**
     * 获取BaseAccessToken的方法
     *
     * @return 微信返回的AccessToken
     * @throws AccessTokenException AccessTokenException
     */
    @Override
    public BaseAccessTokenRequest getBaseAccessToken() throws AccessTokenException {
        // 从缓存中获取AccessToken
        BaseAccessTokenRequest baseAccessTokenRequest = (BaseAccessTokenRequest) cacheService.getValue(ACCESS_TOKEN_NO_OPENID);
        LOGGER.info("[缓存中的基础AccessToken] {}", baseAccessTokenRequest);
        if (baseAccessTokenRequest != null) {
            return baseAccessTokenRequest;
        }
        String urlGetAccessToken = credentialProperties.getAccessToken();
        // 向微信服务器索取accessToken
        String accessTokenJson = HttpUtil.get(urlGetAccessToken);
        // 将AccessToken转换成对象
        BaseAccessTokenRequest accessToken = JSONObject.parseObject(accessTokenJson, BaseAccessTokenRequest.class);
        Integer errCode = accessToken.getErrcode();
        boolean success = isSuccess(errCode);
        if (success) {
            // 成功响应 将AccessToken存入缓存中
            cacheService.cacheValue(ACCESS_TOKEN_NO_OPENID, accessToken, accessToken.getExpires_in());
            return accessToken;
        }
        ResponseCodeFromWx code = getCode(errCode);
        LOGGER.error(code.toString());
        throw new AccessTokenException(code);
    }
}
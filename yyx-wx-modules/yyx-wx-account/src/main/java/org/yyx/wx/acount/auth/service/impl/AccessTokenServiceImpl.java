package org.yyx.wx.acount.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.yyx.wx.commons.vo.pubnum.reponse.AccessToken;
import org.yyx.wx.acount.auth.config.WxPublicNumConfig;
import org.yyx.wx.acount.auth.service.IAccessTokenService;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static org.yyx.wx.commons.constant.CacheKeyConstant.ACCESS_TOKEN_NO_OPENID;


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
     * 获取微信公众号配置对象
     */
    @Resource
    private WxPublicNumConfig wxPublicNumConfig;

    @Resource
    private RedisTemplate<String, AccessToken> redisTemplate;

    /**
     * 获取AccessToken的方法
     * <p>内含使用线程局部变量判断token是否失效</p>
     *
     * @return 微信返回的AccessToken
     */
    @Override
    public AccessToken getAccessToken() {
        AccessToken accessToken = redisTemplate.opsForValue().get(ACCESS_TOKEN_NO_OPENID);
        if (accessToken != null) {
            return accessToken;
        }
        String appID = wxPublicNumConfig.getAppID();
        String appSecret = wxPublicNumConfig.getAppSecret();
        String urlGetAccessToken = wxPublicNumConfig.getUrlGetAccessToken();
        // 获取accessToken
        String accessTokenJson = HttpUtil.get(urlGetAccessToken + appID + "&secret=" + appSecret);
        if (StrUtil.hasEmpty(accessTokenJson)) {
            return null;
        }
        // 将AccessToken转换成对象
        accessToken = JSONObject.parseObject(accessTokenJson, AccessToken.class);
        // 存入缓存中
        redisTemplate.opsForValue().set(ACCESS_TOKEN_NO_OPENID, accessToken);
        // 设置超时时间
        redisTemplate.expire(ACCESS_TOKEN_NO_OPENID, accessToken.getExpires_in(), TimeUnit.SECONDS);
        return accessToken;
    }
}

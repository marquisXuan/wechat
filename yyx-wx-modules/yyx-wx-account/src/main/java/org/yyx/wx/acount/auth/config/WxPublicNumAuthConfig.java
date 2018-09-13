package org.yyx.wx.acount.auth.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信公众号配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-10:26
 */
@Data
@Component
public class WxPublicNumAuthConfig {
    /**
     * WxPublicNumAuthConfig日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WxPublicNumAuthConfig.class);
    /**
     * appId
     */
    @Value("${wx.public_number.app_id}")
    private String appID;
    /**
     * appSecret
     */
    @Value("${wx.public_number.app_secret}")
    private String appSecret;
    /**
     * 获取AccessToken的访问路径
     */
    @Value("${wx.url.get_access_token}")
    private String urlGetAccessToken;
    /**
     * 回调接口URI
     */
    @Value("${wx.url.redirect_uri}")
    private String redirectUri;
    /**
     * 授权URL
     */
    @Value("${wx.url.auth_code}")
    private String urlAuthCode;
    /**
     * Code获取AuthAccessToken的访问路径
     */
    @Value("${wx.url.code_to_auth_token}")
    private String urlCodeToAuthAccessToken;
    /**
     * 用于刷新AuthAcessToken的访问路径
     */
    @Value("${wx.url.refresh_token}")
    private String urlRefreshToken;
    /**
     * 授权时获取用户信息的访问路径
     */
    @Value("${wx.url.user_info}")
    private String urlUserInfo;
    /**
     * 获取用户基本信息
     */
    @Value("${wx.url.base_user_info}")
    private String urlBaseUserInfo;

    public void setRedirectUri(String redirectUri) {
        try {
            this.redirectUri = URLEncoder.encode(redirectUri, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("[处理URL失败] {}", e.getMessage());
        }
    }

}
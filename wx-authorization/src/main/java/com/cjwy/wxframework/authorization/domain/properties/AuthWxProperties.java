package com.cjwy.wxframework.authorization.domain.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信授权的配置
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 11:11 上午
 */
@Data
@ConfigurationProperties(prefix = "auth.wx")
public class AuthWxProperties {

    /**
     * 用户同意授权时请求 code 的 URL
     * /connect/oauth2/authorize?appid=${wx.validate.appid}&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
     */
    private String requestCode;
    /**
     * 静默授权
     */
    private String silenceScope;
    /**
     * 非静默授权
     */
    private String silenceNo;
    /**
     * code -> authAccessToken
     */
    private String code2AuthAccessToken;
    /**
     * 请求用户信息的 URL
     */
    private String requestUserInfoByAuthAccessToken;
    /**
     * 检查用户授权 Token 是否过期 URL
     */
    private String checkAuthAccessToken;
    /**
     * 请求刷新 AccessToken 的 URL
     */
    private String refreshAuthAccessToken;
}

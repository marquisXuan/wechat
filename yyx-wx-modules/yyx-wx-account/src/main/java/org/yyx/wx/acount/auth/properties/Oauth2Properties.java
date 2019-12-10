package org.yyx.wx.acount.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信 OAuth2.0认证协议配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/10 11:57 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat.publicnum.oauth2")
public class Oauth2Properties {

    private String code;
    private String webViewAccessToken;
    private String refreshToken;
    private String userInfo;
    private String checkAccessToken;
}

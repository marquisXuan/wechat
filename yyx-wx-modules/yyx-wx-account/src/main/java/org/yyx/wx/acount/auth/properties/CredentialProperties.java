package org.yyx.wx.acount.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信公众号唯一凭据配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/10 11:38 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat.publicnum.credential")
public class CredentialProperties {

    /**
     * 获取 AccessToken 的 URL 路径
     */
    private String accessToken;
}

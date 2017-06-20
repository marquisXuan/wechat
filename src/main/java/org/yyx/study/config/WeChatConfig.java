package org.yyx.study.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取微信配置信息的配置类
 * Created by 叶云轩 on 2017/6/20-11:25
 * Concat ycountjavaxuan@outlook.com
 */
@Component
@ConfigurationProperties(prefix = "config.wx")
public class WeChatConfig {

    /**
     * 微信公众号填写的自定义token
     */
    private String deafultAccessToken;

    public String getDeafultAccessToken() {
        return deafultAccessToken;
    }

    public void setDeafultAccessToken(String deafultAccessToken) {
        this.deafultAccessToken = deafultAccessToken;
    }
}

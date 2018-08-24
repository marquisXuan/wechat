package org.yyx.wx.acount.auth.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信公众号配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-10:26
 */
@Component
@Data
public class WxPublicNumConfig {

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
     * 公众号页面配置的Token
     */
    @Value("${wx.public_number.config_token}")
    private String configToken;
}
package org.yyx.wx.butt.properties;

import lombok.Data;

/**
 * 公众号配置
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/10/31 - 15:02
 */
@Data
public class PublicNumberProperties {
    /**
     * 与微信公众号后台中: 开发->基本配置->公众号开发信息->开发者ID(AppID)保持一致
     */
    private String app_id;
    /**
     * 与微信公众号后台中: 开发->基本配置->公众号开发信息->开发者密码(AppSecret)保持一致
     */
    private String app_secret;
    /**
     * 与微信公众号后台中: 开发->基本配置->服务器配置->令牌(Token)保持一致
     */
    private String config_token;
}

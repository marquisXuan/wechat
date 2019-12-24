package org.yyx.wx.butt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static org.yyx.wx.commons.constant.HandlerConstant.DEFAULT_HANDLER;

/**
 * 公众号配置
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/10/31 - 15:02
 */
@Data
@ConfigurationProperties("wx.pubnum")
public class DefaultPublicNumberProperties {
    /**
     * 与微信公众号后台中: 开发->基本配置->公众号开发信息->开发者ID(AppID)保持一致
     */
    private String appId;
    /**
     * 与微信公众号后台中: 开发->基本配置->公众号开发信息->开发者密码(AppSecret)保持一致
     */
    private String appSecret;
    /**
     * 与微信公众号后台中: 开发->基本配置->服务器配置->令牌(Token)保持一致
     */
    private String configToken;
    /**
     * 默认处理器方式为使用默认消息处理器
     */
    private String handlerType = DEFAULT_HANDLER;
}

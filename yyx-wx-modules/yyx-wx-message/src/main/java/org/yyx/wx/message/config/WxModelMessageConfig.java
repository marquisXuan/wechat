package org.yyx.wx.message.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信模板消息配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/1-09:45
 */
@Data
@Component
public class WxModelMessageConfig {

    @Value("${wx.url.send_model_message}")
    private String urlSendModelMessage;
}

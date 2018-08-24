package org.yyx.wx.acount.qrcode.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信二维码配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-13:19
 */
@Data
@Component
public class WxQRCodeConfig {
    /**
     * 创建Ticket的URL
     */
    @Value("${wx.url.create_ticket}")
    private String urlCreateTicket;
    /**
     * 通过Ticket换取二维码的URL
     */
    @Value("${wx.url.get_qr_code}")
    private String urlQRCode;
}

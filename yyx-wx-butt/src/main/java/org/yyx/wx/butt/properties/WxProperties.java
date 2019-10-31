package org.yyx.wx.butt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信公众号外置配置
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/10/31 - 14:57
 */
@Data
@ConfigurationProperties(prefix = "wx")
public class WxProperties {

    private PublicNumberProperties public_num;
}

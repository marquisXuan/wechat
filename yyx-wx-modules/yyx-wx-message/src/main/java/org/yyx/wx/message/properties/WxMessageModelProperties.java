package org.yyx.wx.message.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信模板消息配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/11 9:30 上午
 */
@Data
@Component
@ConfigurationProperties(prefix = "cjwy.wechat.publicnum.message.model")
public class WxMessageModelProperties {

    private String setUpIndustry;

    private String getIndustry;

    private String send;
}

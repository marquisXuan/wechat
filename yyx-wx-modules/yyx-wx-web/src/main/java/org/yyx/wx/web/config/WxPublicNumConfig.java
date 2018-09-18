package org.yyx.wx.web.config;

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
@Data
@Component
public class WxPublicNumConfig {

    /**
     * 公众号页面配置的Token
     */
    @Value("${wx.public_number.config_token}")
    private String configToken;

    @Value("${service.handler_type}")
    private String handlerType;

}
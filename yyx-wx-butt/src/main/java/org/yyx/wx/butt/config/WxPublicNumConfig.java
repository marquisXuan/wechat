package org.yyx.wx.butt.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.yyx.wx.butt.properties.WxProperties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 微信公众号配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-10:26
 */
@Configuration
@EnableConfigurationProperties(WxProperties.class)
@Data
public class WxPublicNumConfig {

    /**
     * WxPublicNumConfig 日志输出
     */
    private final static Logger log = LoggerFactory.getLogger(WxPublicNumConfig.class);

    @Resource
    private WxProperties wxProperties;
    /**
     * 公众号页面配置的Token
     */
    @Value("${wx.public_number.config_token}")
    private String configToken;
    @Value("${service.handler_type}")
    private String handlerType;

    @PostConstruct
    public void init() {
        log.info("[init] -> [wx] {} ", wxProperties);
    }

}
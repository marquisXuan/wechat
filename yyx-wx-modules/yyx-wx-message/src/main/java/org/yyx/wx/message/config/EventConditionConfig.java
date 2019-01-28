package org.yyx.wx.message.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yyx.wx.message.proxy.event.SubscribeEventHandlerProxy;
import org.yyx.wx.message.service.impl.demo.event.DefaultSubscribeEventServiceImpl;

/**
 * 默认处理类配置
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019-01-28-13:14
 */
@Configuration
public class EventConditionConfig {

    /**
     * SubscribeEventHandlerProxy 默认配置
     *
     * @return SubscribeEventHandlerProxy
     */
    @Bean
    @ConditionalOnMissingBean(SubscribeEventHandlerProxy.class)
    public SubscribeEventHandlerProxy subscribeEventHandlerProxy() {
        return new DefaultSubscribeEventServiceImpl();
    }
}

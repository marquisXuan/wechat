package org.yyx.wx.message.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.yyx.wx.message.condition.TextMessageServiceCondition;
import org.yyx.wx.message.proxy.message.TextMessageHandlerProxy;
import org.yyx.wx.message.service.impl.demo.DemoTextMessageServiceImpl;

/**
 * 消息处理器配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/10-18:52
 */
@Configuration
public class HandlerConditionConfig {

    @Bean
    @Conditional(TextMessageServiceCondition.class)
    public TextMessageHandlerProxy textMessageHandlerProxy() {
        // 没有自定义的业务，就返回默认的业务处理
        return new DemoTextMessageServiceImpl();
    }
}

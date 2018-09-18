package org.yyx.wx.message.builder;

import org.yyx.wx.message.handler.AbstractMessageHandler;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;

/**
 * 自定义消息事件处理器建造者
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/17-16:55
 */
public class CustomerHandlerBuilder extends BaseHandlerBuilder {

    /**
     * 创建对象
     */
    private static final CustomerHandlerBuilder CUSTOMER_HANDLER = new CustomerHandlerBuilder();

    /**
     * 私有构造
     */
    private CustomerHandlerBuilder() {
    }

    /**
     * 获取对象
     *
     * @return 返回对象
     */
    public static CustomerHandlerBuilder getInstance() {
        return CUSTOMER_HANDLER;
    }


    /**
     * 创建一个默认链条，只包含自定义的消息处理链
     *
     * @param abstractMessageHandlers   自定义消息处理链数组
     * @param baseMessageHandlerProxies 代理类集合
     * @return 封装好的处理链
     */
    @Override
    public AbstractMessageHandler getHandler(AbstractMessageHandler[] abstractMessageHandlers, BaseMessageHandlerProxy[] baseMessageHandlerProxies) {
        return getCustomerHandler(abstractMessageHandlers, baseMessageHandlerProxies);
    }
}

package org.yyx.wx.message.builder;

import org.yyx.wx.message.handler.AbstractMessageHandler;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;

/**
 * 默认的消息处理建造者
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/17-17:14
 */
public class DefaultHandlerBuilder extends BaseHandlerBuilder {

    /**
     * 创建对象
     */
    private static final DefaultHandlerBuilder DEFAULT_HANDLER_BUILDER = new DefaultHandlerBuilder();

    /**
     * 私有构造
     */
    private DefaultHandlerBuilder() {
    }

    /**
     * 获取对象
     *
     * @return 返回对象
     */
    public static DefaultHandlerBuilder getInstance() {
        return DEFAULT_HANDLER_BUILDER;
    }

    /**
     * 创建一个默认链条，只包含默认处理链
     *
     * @param abstractMessageHandlers   自定义消息处理链数组
     * @param baseMessageHandlerProxies 代理类集合
     * @return 封装好的处理链
     */
    @Override
    public AbstractMessageHandler getHandler(AbstractMessageHandler[] abstractMessageHandlers, BaseMessageHandlerProxy[] baseMessageHandlerProxies) {
        return getDefaultHandler(baseMessageHandlerProxies);
    }
}

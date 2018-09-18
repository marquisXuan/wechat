package org.yyx.wx.message.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.message.handler.AbstractMessageHandler;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;

/**
 * 消息事件处理器建造者
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/3-16:28
 */
public class DefaultAndCustomerHandlerBuilder extends BaseHandlerBuilder {
    /**
     * DefaultAndCustomerHandlerBuilder 日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAndCustomerHandlerBuilder.class);
    /**
     * 创建对象
     */
    private static final DefaultAndCustomerHandlerBuilder MESSAGE_EVENT_HANDLER_FACTORY = new DefaultAndCustomerHandlerBuilder();

    /**
     * 私有构造
     */
    private DefaultAndCustomerHandlerBuilder() {
    }

    /**
     * 获取对象
     *
     * @return 返回对象
     */
    public static DefaultAndCustomerHandlerBuilder getInstance() {
        return MESSAGE_EVENT_HANDLER_FACTORY;
    }

    /**
     * 获取事件处理器，最大支持11个额外链条
     * 链条设置
     * 未关注扫描二维码 -> 关注过扫描二维码 -> 订阅[关注] -> 文本消息
     * -> 链接消息处理器 -> 图片消息处理器 -> 语音消息处理器 -> 小视频消息处理器
     * -> 视频消息处理器 -> 地理位置消息处理器 -> 取消订阅[关注]公众号事件处理器 -> 模板消息推送事件处理器
     *
     * @param abstractMessageHandlers   自定义消息处理链数组
     * @param baseMessageHandlerProxies 代理类集合
     * @return 事件处理器
     */
    @Override
    public AbstractMessageHandler getHandler(final AbstractMessageHandler[] abstractMessageHandlers, BaseMessageHandlerProxy[] baseMessageHandlerProxies) {
        AbstractMessageHandler lastAbstractMessageHandler = getCustomerHandler(abstractMessageHandlers, baseMessageHandlerProxies);
        AbstractMessageHandler defaultHandler = getDefaultHandler(baseMessageHandlerProxies);
        if (lastAbstractMessageHandler != null) {
            lastAbstractMessageHandler.setNextHandler(defaultHandler);
            lastAbstractMessageHandler.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            // 优先加载自定义消息处理器
            return lastAbstractMessageHandler;
        } else {
            LOGGER.info("[没有设置自定义消息处理器。此次返回默认消息处理器] ");
            return defaultHandler;
        }
    }
}

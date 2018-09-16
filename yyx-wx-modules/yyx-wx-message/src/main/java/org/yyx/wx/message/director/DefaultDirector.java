package org.yyx.wx.message.director;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.message.builder.MessageEventHandlerBuilder;
import org.yyx.wx.message.handler.AbstractMessageHandler;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;

/**
 * 默认导演
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/16-08:59
 */
public class DefaultDirector {

    /**
     * DefaultDirector 日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDirector.class);
    /**
     * 默认的消息事件处理器
     */
    private static final MessageEventHandlerBuilder MESSAGE_EVENT_HANDLER_BUILDER = MessageEventHandlerBuilder.getInstance();

    /**
     * 创建一个默认链条，包含默认处理链和自定义处理链
     * <p>
     * 自定义处理链 -> 未关注扫描二维码 -> 关注过扫描二维码 -> 订阅[关注] -> 文本消息
     * -> 链接消息处理器 -> 图片消息处理器 -> 语音消息处理器 -> 小视频消息处理器
     * -> 视频消息处理器 -> 地理位置消息处理器 -> 取消订阅[关注]公众号事件处理器 -> 模板消息推送事件处理器
     *
     * @param abstractMessageHandlers   自定义消息处理链数组
     * @param baseMessageHandlerProxies 代理类集合
     * @return 封装好的处理链
     */
    public static AbstractMessageHandler getDefaultAndCustomerBuilder(AbstractMessageHandler[] abstractMessageHandlers, BaseMessageHandlerProxy[] baseMessageHandlerProxies) {
        LOGGER.info("[默认导演创建默认消息处理链条融合自定义消息处理链] ");
        AbstractMessageHandler messageHandler = MESSAGE_EVENT_HANDLER_BUILDER.getDefaultAndCustomerHandler(abstractMessageHandlers, baseMessageHandlerProxies);
        LOGGER.info("[创建默认消息处理链条融合自定义消息处理链链条杀青] ");
        return messageHandler;
    }

    /**
     * 创建一个默认链条，不包含自定义处理链
     * 默认链条顺序为：
     * <p>
     * 未关注扫描二维码 -> 关注过扫描二维码 -> 订阅[关注] -> 文本消息
     * -> 链接消息处理器 -> 图片消息处理器 -> 语音消息处理器 -> 小视频消息处理器
     * -> 视频消息处理器 -> 地理位置消息处理器 -> 取消订阅[关注]公众号事件处理器 -> 模板消息推送事件处理器
     *
     * @param baseMessageHandlerProxies 自定义处理链顺序
     * @return 封装好的处理链
     */
    public static AbstractMessageHandler getDefaultBuilder(BaseMessageHandlerProxy[] baseMessageHandlerProxies) {
        LOGGER.info("[默认导演创建默认消息处理链] ");
        AbstractMessageHandler messageHandler = MESSAGE_EVENT_HANDLER_BUILDER.getDefaultHandler(baseMessageHandlerProxies);
        LOGGER.info("[创建默认消息处理链条杀青] ");
        return messageHandler;
    }
}
package org.yyx.wx.message.builder;

import cn.hutool.core.util.ArrayUtil;
import org.yyx.wx.message.handler.AbstractMessageHandler;
import org.yyx.wx.message.handler.event.SubscribeEventHandler;
import org.yyx.wx.message.handler.event.SubscribeScanEventHandler;
import org.yyx.wx.message.handler.event.UnSubscribeEventHandler;
import org.yyx.wx.message.handler.event.UnSubscribeScanEventHandler;
import org.yyx.wx.message.handler.message.ImageMessageHandler;
import org.yyx.wx.message.handler.message.LinkMessageHandler;
import org.yyx.wx.message.handler.message.LocationMessageHandler;
import org.yyx.wx.message.handler.message.ShortVideoMessageHandler;
import org.yyx.wx.message.handler.message.TextMessageHandler;
import org.yyx.wx.message.handler.message.VideoMessageHandler;
import org.yyx.wx.message.handler.message.VoiceMessageHandler;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;

import static org.yyx.wx.commons.constant.HandlerConstant.MAX_HANDLER_COUNT;

/**
 * 消息事件处理器建造者
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/3-16:28
 */
public class MessageEventHandlerBuilder {
    // region 依赖
    /**
     * 创建一个事件处理器集合
     */
    public static final AbstractMessageHandler[] ABSTRACT_MESSAGE_HANDLERS = new AbstractMessageHandler[MAX_HANDLER_COUNT - 11];
    /**
     * 图片消息处理器
     */
    private static final ImageMessageHandler IMAGE_MESSAGE_HANDLER = ImageMessageHandler.getInstance();
    /**
     * 链接消息处理器
     */
    private static final LinkMessageHandler LINK_MESSAGE_HANDLER = LinkMessageHandler.getInstance();
    /**
     * 地理位置消息处理器
     */
    private static final LocationMessageHandler LOCATION_MESSAGE_HANDLER = LocationMessageHandler.getInstance();
    /**
     * 创建对象
     */
    private static final MessageEventHandlerBuilder MESSAGE_EVENT_HANDLER_FACTORY = new MessageEventHandlerBuilder();
    /**
     * 小视频消息处理器
     */
    private static final ShortVideoMessageHandler SHORT_VIDEO_MESSAGE_HANDLER = ShortVideoMessageHandler.getInstance();
    /**
     * 订阅[关注]事件处理器
     */
    private static final SubscribeEventHandler SUBSCRIBE_EVENT_HANDLER = SubscribeEventHandler.getInstance();
    /**
     * 关注过扫描二维码事件处理器
     */
    private static final SubscribeScanEventHandler SUBSCRIBE_SCAN_EVENT_HANDLER = SubscribeScanEventHandler.getInstance();
    /**
     * 文本消息处理器
     */
    private static final TextMessageHandler TEXT_MESSAGE_HANDLER = TextMessageHandler.getInstance();
    /**
     * 取消订阅[关注]公众号事件处理器
     */
    private static final UnSubscribeEventHandler UN_SUBSCRIBE_EVENT_HANDLER = UnSubscribeEventHandler.getInstance();
    /**
     * 未关注扫描二维码事件处理器
     */
    private static final UnSubscribeScanEventHandler UN_SUBSCRIBE_SCAN_EVENT_HANDLER = UnSubscribeScanEventHandler.getInstance();
    /**
     * 视频消息处理器
     */
    private static final VideoMessageHandler VIDEO_MESSAGE_HANDLER = VideoMessageHandler.getInstance();
    /**
     * 语音消息处理器
     */
    private static final VoiceMessageHandler VOICE_MESSAGE_HANDLER = VoiceMessageHandler.getInstance();
    // endregion

    /**
     * 私有构造
     */
    private MessageEventHandlerBuilder() {
    }

    /**
     * 获取对象
     *
     * @return 返回对象
     */
    public static MessageEventHandlerBuilder getInstance() {
        return MESSAGE_EVENT_HANDLER_FACTORY;
    }

    /**
     * 获取事件处理器，最大支持11个额外链条
     * 链条设置
     * 未关注扫描二维码 -> 关注过扫描二维码 -> 订阅[关注] -> 文本消息
     * -> 链接消息处理器 -> 图片消息处理器 -> 语音消息处理器 -> 小视频消息处理器
     * -> 视频消息处理器 -> 地理位置消息处理器 -> 取消订阅[关注]公众号事件处理器
     *
     * @param baseMessageHandlerProxies 代理类
     * @return 事件处理器
     */
    public static AbstractMessageHandler getMessageHandler(BaseMessageHandlerProxy[] baseMessageHandlerProxies) {
        // 添加自定义消息处理器
        if (!ArrayUtil.hasNull(ABSTRACT_MESSAGE_HANDLERS)) {
            // 是否有自定义链条
            for (int i = ABSTRACT_MESSAGE_HANDLERS.length - 1; i > 0; i--) {
                AbstractMessageHandler abstractMessageHandler = ABSTRACT_MESSAGE_HANDLERS[i];
                if (abstractMessageHandler == null) {
                    continue;
                }
                // 下一个自定义消息处理器
                AbstractMessageHandler nextAbstractMessageHandler = ABSTRACT_MESSAGE_HANDLERS[i - 1];
                abstractMessageHandler.setNextHandler(nextAbstractMessageHandler);
                abstractMessageHandler.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            }
        }
        // 获取第一个消息处理器，主要用于添加到基本消息处理器中
        AbstractMessageHandler lastAbstractMessageHandler = ABSTRACT_MESSAGE_HANDLERS[0];
        if (!UN_SUBSCRIBE_SCAN_EVENT_HANDLER.hasNext()) {
            // 设置链条
            UN_SUBSCRIBE_EVENT_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            LOCATION_MESSAGE_HANDLER.setNextHandler(UN_SUBSCRIBE_EVENT_HANDLER);
            LOCATION_MESSAGE_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            VIDEO_MESSAGE_HANDLER.setNextHandler(LOCATION_MESSAGE_HANDLER);
            VIDEO_MESSAGE_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            SHORT_VIDEO_MESSAGE_HANDLER.setNextHandler(VIDEO_MESSAGE_HANDLER);
            SHORT_VIDEO_MESSAGE_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            VOICE_MESSAGE_HANDLER.setNextHandler(SHORT_VIDEO_MESSAGE_HANDLER);
            VOICE_MESSAGE_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            IMAGE_MESSAGE_HANDLER.setNextHandler(VOICE_MESSAGE_HANDLER);
            IMAGE_MESSAGE_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            LINK_MESSAGE_HANDLER.setNextHandler(IMAGE_MESSAGE_HANDLER);
            LINK_MESSAGE_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            TEXT_MESSAGE_HANDLER.setNextHandler(LINK_MESSAGE_HANDLER);
            TEXT_MESSAGE_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            SUBSCRIBE_EVENT_HANDLER.setNextHandler(TEXT_MESSAGE_HANDLER);
            SUBSCRIBE_EVENT_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            SUBSCRIBE_SCAN_EVENT_HANDLER.setNextHandler(SUBSCRIBE_EVENT_HANDLER);
            SUBSCRIBE_SCAN_EVENT_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            UN_SUBSCRIBE_SCAN_EVENT_HANDLER.setNextHandler(SUBSCRIBE_SCAN_EVENT_HANDLER);
            UN_SUBSCRIBE_SCAN_EVENT_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
        }
        if (lastAbstractMessageHandler != null) {
            lastAbstractMessageHandler.setNextHandler(UN_SUBSCRIBE_SCAN_EVENT_HANDLER);
            lastAbstractMessageHandler.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            // 优先加载自定义消息处理器
            return lastAbstractMessageHandler;
        } else {
            return UN_SUBSCRIBE_SCAN_EVENT_HANDLER;
        }
    }
}

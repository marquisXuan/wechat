package org.yyx.wx.message.builder;

import cn.hutool.core.util.ArrayUtil;
import org.yyx.wx.commons.exception.handler.OutOfOverMaxHandlerException;
import org.yyx.wx.message.handler.AbstractMessageHandler;
import org.yyx.wx.message.handler.event.model.ModelMessagePushEventHandler;
import org.yyx.wx.message.handler.event.subscribe.SubscribeEventHandler;
import org.yyx.wx.message.handler.event.scan.SubscribeScanEventHandler;
import org.yyx.wx.message.handler.event.subscribe.UnSubscribeEventHandler;
import org.yyx.wx.message.handler.event.scan.UnSubscribeScanEventHandler;
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
 * 建造者抽象类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/16-08:52
 */
public abstract class BaseHandlerBuilder {

    // region 依赖
    /**
     * 图片消息处理器
     */
    protected static final ImageMessageHandler IMAGE_MESSAGE_HANDLER = ImageMessageHandler.getInstance();
    /**
     * 链接消息处理器
     */
    protected static final LinkMessageHandler LINK_MESSAGE_HANDLER = LinkMessageHandler.getInstance();
    /**
     * 地理位置消息处理器
     */
    protected static final LocationMessageHandler LOCATION_MESSAGE_HANDLER = LocationMessageHandler.getInstance();
    /**
     * 模板消息推送事件处理器
     */
    protected static final ModelMessagePushEventHandler MODEL_MESSAGE_PUSH_EVENT_HANDLER = ModelMessagePushEventHandler.getInstance();
    /**
     * 小视频消息处理器
     */
    protected static final ShortVideoMessageHandler SHORT_VIDEO_MESSAGE_HANDLER = ShortVideoMessageHandler.getInstance();
    /**
     * 订阅[关注]事件处理器
     */
    protected static final SubscribeEventHandler SUBSCRIBE_EVENT_HANDLER = SubscribeEventHandler.getInstance();
    /**
     * 关注过扫描二维码事件处理器
     */
    protected static final SubscribeScanEventHandler SUBSCRIBE_SCAN_EVENT_HANDLER = SubscribeScanEventHandler.getInstance();
    /**
     * 文本消息处理器
     */
    protected static final TextMessageHandler TEXT_MESSAGE_HANDLER = TextMessageHandler.getInstance();
    /**
     * 取消订阅[关注]公众号事件处理器
     */
    protected static final UnSubscribeEventHandler UN_SUBSCRIBE_EVENT_HANDLER = UnSubscribeEventHandler.getInstance();
    /**
     * 未关注扫描二维码事件处理器
     */
    protected static final UnSubscribeScanEventHandler UN_SUBSCRIBE_SCAN_EVENT_HANDLER = UnSubscribeScanEventHandler.getInstance();
    /**
     * 视频消息处理器
     */
    protected static final VideoMessageHandler VIDEO_MESSAGE_HANDLER = VideoMessageHandler.getInstance();
    /**
     * 语音消息处理器
     */
    protected static final VoiceMessageHandler VOICE_MESSAGE_HANDLER = VoiceMessageHandler.getInstance();
    // endregion

    /**
     * 获取自定义的事件处理链
     *
     * @param abstractMessageHandlers   自定义消息处理链数组
     * @param baseMessageHandlerProxies 自定义处理链顺序
     * @return 封装好的处理链
     */
    protected AbstractMessageHandler getCustomerHandler(final AbstractMessageHandler[] abstractMessageHandlers
            , BaseMessageHandlerProxy[] baseMessageHandlerProxies) {
        // 添加自定义消息处理器
        if (!ArrayUtil.hasNull(abstractMessageHandlers)) {
            // 传入自定义链条数组长度
            int handlerLength = abstractMessageHandlers.length;
            int maxHandlerCount = MAX_HANDLER_COUNT - 11;
            boolean b = maxHandlerCount == Math.max(handlerLength, maxHandlerCount);
            if (!b) {
                throw new OutOfOverMaxHandlerException(handlerLength);
            }
            // 是否有自定义链条
            for (int i = handlerLength - 1; i > 0; i--) {
                AbstractMessageHandler abstractMessageHandler = abstractMessageHandlers[i];
                if (abstractMessageHandler == null) {
                    continue;
                }
                // 下一个自定义消息处理器
                AbstractMessageHandler nextAbstractMessageHandler = abstractMessageHandlers[i - 1];
                abstractMessageHandler.setNextHandler(nextAbstractMessageHandler);
                abstractMessageHandler.setBaseMessageHandlerProxy(baseMessageHandlerProxies);
            }
        }
        // 获取第一个消息处理器，主要用于添加到基本消息处理器中
        return abstractMessageHandlers[0];
    }

    /**
     * 创建一个默认链条，不包含自定义处理链
     * 默认链条顺序为：
     * <p>
     * <p>
     * * 未关注扫描二维码 -> 关注过扫描二维码 -> 订阅[关注] -> 文本消息
     * * -> 链接消息处理器 -> 图片消息处理器 -> 语音消息处理器 -> 小视频消息处理器
     * * -> 视频消息处理器 -> 地理位置消息处理器 -> 取消订阅[关注]公众号事件处理器 -> 模板消息推送事件处理器
     * *
     *
     * @param baseMessageHandlerProxies 自定义处理链顺序
     * @return 封装好的处理链
     */
    protected AbstractMessageHandler getDefaultHandler(BaseMessageHandlerProxy[] baseMessageHandlerProxies) {
        if (!UN_SUBSCRIBE_SCAN_EVENT_HANDLER.hasNext()) {
            // 设置链条
            MODEL_MESSAGE_PUSH_EVENT_HANDLER.setBaseMessageHandlerProxy(baseMessageHandlerProxies);

            UN_SUBSCRIBE_EVENT_HANDLER.setNextHandler(MODEL_MESSAGE_PUSH_EVENT_HANDLER);
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
        return UN_SUBSCRIBE_SCAN_EVENT_HANDLER;
    }

    /**
     * 创建一个默认链条，包含默认处理链和自定义处理链
     *
     * @param abstractMessageHandlers   自定义消息处理链数组
     * @param baseMessageHandlerProxies 自定义处理链顺序
     * @return 封装好的处理链
     */
    public abstract AbstractMessageHandler getHandler(final AbstractMessageHandler[] abstractMessageHandlers, BaseMessageHandlerProxy[] baseMessageHandlerProxies);
}
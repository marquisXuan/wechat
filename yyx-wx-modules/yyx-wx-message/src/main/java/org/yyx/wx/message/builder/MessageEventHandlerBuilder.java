package org.yyx.wx.message.builder;

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

import static org.yyx.wx.commons.constant.HandlerConstant.MAX_HANDLER_COUNT;

/**
 * 消息事件处理器建造者
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/3-16:28
 */
public class MessageEventHandlerBuilder {
    /**
     * 创建一个事件处理器集合
     */
    public static final AbstractMessageHandler[] ABSTRACT_MESSAGE_HANDLERS = new AbstractMessageHandler[MAX_HANDLER_COUNT - 11];
    /**
     * 创建对象
     */
    private static final MessageEventHandlerBuilder MESSAGE_EVENT_HANDLER_FACTORY = new MessageEventHandlerBuilder();

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
     * @return 事件处理器
     */
    public static AbstractMessageHandler getMessageHandler() {
        // 订阅[关注]事件处理器
        SubscribeEventHandler subscribeEventHandler = SubscribeEventHandler.getInstance();
        // 关注过扫描二维码事件处理器
        SubscribeScanEventHandler subscribeScanEventHandler = SubscribeScanEventHandler.getInstance();
        // 未关注扫描二维码事件处理器
        UnSubscribeScanEventHandler unSubscribeScanEventHandler = UnSubscribeScanEventHandler.getInstance();
        // 取消订阅[关注]公众号事件处理器
        UnSubscribeEventHandler unSubscribeEventHandler = UnSubscribeEventHandler.getInstance();
        // 图片消息处理器
        ImageMessageHandler imageMessageHandler = ImageMessageHandler.getInstance();
        // 链接消息处理器
        LinkMessageHandler linkMessageHandler = LinkMessageHandler.getInstance();
        // 地理位置消息处理器
        LocationMessageHandler locationMessageHandler = LocationMessageHandler.getInstance();
        // 小视频消息处理器
        ShortVideoMessageHandler shortVideoMessageHandler = ShortVideoMessageHandler.getInstance();
        // 文本消息处理器
        TextMessageHandler textMessageHandler = TextMessageHandler.getInstance();
        // 视频消息处理器
        VideoMessageHandler videoMessageHandler = VideoMessageHandler.getInstance();
        // 语音消息处理器
        VoiceMessageHandler voiceMessageHandler = VoiceMessageHandler.getInstance();
        // 添加自定义消息处理器
        for (int i = ABSTRACT_MESSAGE_HANDLERS.length; i > 0; i--) {
            AbstractMessageHandler abstractMessageHandler = ABSTRACT_MESSAGE_HANDLERS[i];
            if (abstractMessageHandler == null) {
                break;
            }
            // 下一个自定义消息处理器
            AbstractMessageHandler nextAbstractMessageHandler = ABSTRACT_MESSAGE_HANDLERS[i - 1];
            abstractMessageHandler.setNextHandler(nextAbstractMessageHandler);
        }
        // 获取第一个消息处理器，主要用于添加到基本消息处理器中
        AbstractMessageHandler lastAbstractMessageHandler = ABSTRACT_MESSAGE_HANDLERS[0];
        // 设置链条
        locationMessageHandler.setNextHandler(unSubscribeEventHandler);
        videoMessageHandler.setNextHandler(locationMessageHandler);
        shortVideoMessageHandler.setNextHandler(videoMessageHandler);
        voiceMessageHandler.setNextHandler(shortVideoMessageHandler);
        imageMessageHandler.setNextHandler(voiceMessageHandler);
        linkMessageHandler.setNextHandler(imageMessageHandler);
        textMessageHandler.setNextHandler(linkMessageHandler);
        subscribeEventHandler.setNextHandler(textMessageHandler);
        subscribeScanEventHandler.setNextHandler(subscribeEventHandler);
        unSubscribeScanEventHandler.setNextHandler(subscribeScanEventHandler);
        lastAbstractMessageHandler.setNextHandler(unSubscribeScanEventHandler);
        // 优先自定义消息处理器
        return lastAbstractMessageHandler;
    }
}

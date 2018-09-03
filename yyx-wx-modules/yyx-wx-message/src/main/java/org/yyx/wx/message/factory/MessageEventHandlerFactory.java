package org.yyx.wx.message.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
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

/**
 * 消息事件处理器工厂
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/3-16:28
 */
public class MessageEventHandlerFactory {
    /**
     * 创建一个事件处理器集合
     */
    public static final AbstractMessageHandler[] ABSTRACT_MESSAGE_HANDLERS = new AbstractMessageHandler[11];
    /**
     * 创建对象
     */
    private static final MessageEventHandlerFactory MESSAGE_EVENT_HANDLER_FACTORY = new MessageEventHandlerFactory();

    /**
     * 私有构造
     */
    private MessageEventHandlerFactory() {
    }

    /**
     * 获取对象
     *
     * @return 返回对象
     */
    public static MessageEventHandlerFactory getInstance() {
        return MESSAGE_EVENT_HANDLER_FACTORY;
    }

    /**
     * 获取事件处理器，最大支持11个额外链条
     * 链条设置
     * 未关注扫描二维码 -> 关注过扫描二维码 -> 订阅[关注] -> 文本消息
     * -> 链接消息处理器 -> 图片消息处理器 -> 语音消息处理器 -> 小视频消息处理器
     * -> 视频消息处理器 -> 地理位置消息处理器 -> 取消订阅[关注]公众号事件处理器
     *
     * @param redisTemplate      注入Redis缓存，有待优化
     * @param applicationContext 注入Spring容器，有待优化
     * @return 事件处理器
     */
    public static AbstractMessageHandler getMessageHandler(RedisTemplate<Object, Object> redisTemplate,
                                                           ApplicationContext applicationContext) {
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
        for (int i = 0; i < ABSTRACT_MESSAGE_HANDLERS.length; i++) {
            AbstractMessageHandler abstractMessageHandler = ABSTRACT_MESSAGE_HANDLERS[i];
            // 下一个
            if (i < ABSTRACT_MESSAGE_HANDLERS.length - 1) {
                AbstractMessageHandler nextAbstractMessageHandler = ABSTRACT_MESSAGE_HANDLERS[i + 1];
                abstractMessageHandler.setNextHandler(nextAbstractMessageHandler);
            }
        }
        AbstractMessageHandler lastAbstractMessageHandler = ABSTRACT_MESSAGE_HANDLERS[0];
        // 设置链条
        unSubscribeEventHandler.setNextHandler(lastAbstractMessageHandler);
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
        if (redisTemplate != null) {
            unSubscribeScanEventHandler.setRedisTemplate(redisTemplate);
        }
        if (applicationContext != null) {
            unSubscribeScanEventHandler.setStaticApplicationContext(applicationContext);
        }
        return unSubscribeScanEventHandler;
    }
}

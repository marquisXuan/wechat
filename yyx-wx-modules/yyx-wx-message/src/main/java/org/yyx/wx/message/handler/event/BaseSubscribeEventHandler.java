package org.yyx.wx.message.handler.event;

import cn.hutool.core.util.StrUtil;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.util.XmlToObjectUtil;
import org.yyx.wx.commons.vo.pubnum.BaseMessageAndEvent;
import org.yyx.wx.commons.vo.pubnum.request.event.SubscribeAndUnSubscribeScanEventRequest;

/**
 * 微信发来的关注事件
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/28-16:04
 */
public abstract class BaseSubscribeEventHandler extends BaseEventHandler {

    /**
     * BaseEventHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseEventHandler.class);

    /**
     * 事件处理器的模板方法
     *
     * @param baseMessageRequest 处理器
     * @return 消息
     */
    @Override
    public final BaseMessageAndEvent handleMessage(BaseMessageAndEvent baseMessageRequest, Element element) {
        LOGGER.info("[订阅事件分发器] ");
        BaseMessageAndEvent baseMessage = null;
        // 都使用扫码事件实体来接收
        SubscribeAndUnSubscribeScanEventRequest subscribeAndUnSubscribeScanEventRequest;
        try {
            subscribeAndUnSubscribeScanEventRequest = XmlToObjectUtil.xmlToObject(element, SubscribeAndUnSubscribeScanEventRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return baseMessage;
        }
        // 获取订阅事件的事件处理级别 eventKey=null:订阅关注事件  eventKey=qrscene_xxxxxx:扫码事件
        String eventKey = subscribeAndUnSubscribeScanEventRequest.getEventKey();
        String level = eventKey;
        if (!StrUtil.hasEmpty(eventKey)) {
            level = eventKey.substring(0, "qrscene_".length());
        }
        LOGGER.info("[level] {}", level);
        if (StrUtil.hasEmpty(this.getHandlerLevel())) {
            if (nextHandler == null) {
                // todo do something
                LOGGER.error("[没有可以处理该订阅类型事件的处理器]");
                return baseMessage;
            }
            baseMessage = nextHandler.handleMessage(baseMessageRequest, element);
        } else if (this.getHandlerLevel().equals(level)) {
            baseMessage = this.dealTask(element);
        } else {
            if (nextHandler != null) {
                baseMessage = nextHandler.handleMessage(baseMessageRequest, element);
            } else {
                // todo do something
                LOGGER.error("[没有可以处理该订阅类型事件的处理器]");
                return baseMessage;
            }
        }
        return baseMessage;
    }
}

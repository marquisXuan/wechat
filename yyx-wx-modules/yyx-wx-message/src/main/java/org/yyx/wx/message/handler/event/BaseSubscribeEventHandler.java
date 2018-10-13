package org.yyx.wx.message.handler.event;

import cn.hutool.core.util.StrUtil;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.bussinessenum.EventTypeEnum;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.BaseMessageAndEventRequestAndResponse;
import org.yyx.wx.commons.vo.pubnum.request.event.SubscribeAndUnSubscribeScanEventRequest;
import org.yyx.wx.commons.vo.pubnum.response.message.BaseMessageResponse;

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
    public final BaseMessageResponse handleMessage(BaseMessageAndEventRequestAndResponse baseMessageRequest, Element element) {
        // 都使用扫码事件实体来接收
        SubscribeAndUnSubscribeScanEventRequest subscribeAndUnSubscribeScanEventRequest;
        try {
            subscribeAndUnSubscribeScanEventRequest = WxXmlAndObjectUtil.xmlToObject(element, SubscribeAndUnSubscribeScanEventRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        /*
         获取订阅事件的事件处理级别 event 均为 subscribe
         eventKey=null:订阅关注事件
         eventKey=qrscene_xxxxxx:扫码事件
          */
        String event = subscribeAndUnSubscribeScanEventRequest.getEvent();
        String handlerLevel = this.getHandlerLevel();
        String eventKey = subscribeAndUnSubscribeScanEventRequest.getEventKey();
        String subHandlerLevel = this.getSubHandlerLevel();
        String className = this.getClass().getName();
        LOGGER.info("{} -> [关注公众号事件分发器] [当前事件处理器可处理事件类型] {}", className, EventTypeEnum.getDesc(handlerLevel));
        if (handlerLevel.equals(event)) {
            boolean emptyEventKey = StrUtil.hasEmpty(eventKey);
            LOGGER.info("{} -> [微信推送事件二级类型] {}", className, EventTypeEnum.getDesc(eventKey));
            if (StrUtil.hasEmpty(subHandlerLevel)) {
                // 当前是订阅事件处理器
                if (emptyEventKey) {
                    // 微信请求的也是订阅事件
                    return this.dealTask(element);
                }
                if (nextHandler != null) {
                    return nextHandler.handleMessage(baseMessageRequest, element);
                } else {
                    LOGGER.error("[没有可以处理该订阅类型事件的处理器]");
                    return null;
                }
            }
            if (!emptyEventKey) {
                eventKey = eventKey.substring(0, EventTypeEnum.qrscene_.toString().length());
            }
            LOGGER.info("{} -> [当前事件处理器可处理二级事件类型] {}", className, EventTypeEnum.getDesc(subHandlerLevel));
            // 当前不是订阅事件 说明 subHandlerLevel != null
            if (subHandlerLevel.equals(eventKey)) {
                return this.dealTask(element);
            }
            if (nextHandler != null) {
                return nextHandler.handleMessage(baseMessageRequest, element);
            } else {
                LOGGER.error("[没有可以处理该订阅类型事件的处理器]");
                return null;
            }
        } else {
            if (nextHandler != null) {
                return nextHandler.handleMessage(baseMessageRequest, element);
            } else {
                LOGGER.error("[没有可以处理该订阅类型事件的处理器]");
                return null;
            }
        }
    }

    /**
     * 获取二级类型
     *
     * @return 二级类型
     */
    protected abstract String getSubHandlerLevel();
}

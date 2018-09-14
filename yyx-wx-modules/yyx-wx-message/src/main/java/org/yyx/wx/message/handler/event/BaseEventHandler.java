package org.yyx.wx.message.handler.event;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.exception.CreateObjectException;
import org.yyx.wx.commons.exception.handler.NoSuitedHandlerException;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.response.message.BaseMessageResponse;
import org.yyx.wx.commons.vo.pubnum.BaseMessageAndEventRequestAndResponse;
import org.yyx.wx.commons.vo.pubnum.request.event.BaseEventRequest;
import org.yyx.wx.message.handler.AbstractMessageHandler;

/**
 * 事件分发处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-19:59
 */
public abstract class BaseEventHandler extends AbstractMessageHandler {

    /**
     * BaseEventHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseEventHandler.class);

    /**
     * 事件处理器的模板方法
     *
     * @param baseMessageRequest 处理实体父类
     * @return 消息
     */
    @Override
    public BaseMessageResponse handleMessage(BaseMessageAndEventRequestAndResponse baseMessageRequest, Element element) {
        BaseMessageResponse baseMessage;
        BaseEventRequest baseEventRequest;
        try {
            baseEventRequest = WxXmlAndObjectUtil.xmlToObject(element, BaseEventRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new CreateObjectException("解析成[BaseEventRequest|基础事件消息父类]失败");
        }
        LOGGER.info("[微信推送事件分发器]");
        if (this.getHandlerLevel().equals(baseEventRequest.getEvent())) {
            baseMessage = this.dealTask(element);
        } else {
            if (nextHandler != null) {
                baseMessage = nextHandler.handleMessage(baseMessageRequest, element);
            } else {
                throw new NoSuitedHandlerException("没有可以处理该类型事件的处理器");
            }
        }
        return baseMessage;
    }
}
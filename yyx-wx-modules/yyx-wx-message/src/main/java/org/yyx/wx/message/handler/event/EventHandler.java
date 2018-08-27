package org.yyx.wx.message.handler.event;

import org.dom4j.Element;
import org.yyx.wx.commons.util.XmlToObjectUtil;
import org.yyx.wx.commons.vo.pubnum.BaseMessage;
import org.yyx.wx.commons.vo.pubnum.request.event.BaseEventRequest;
import org.yyx.wx.message.handler.AbstractMessageHandler;

/**
 * 事件处理器 - 该类不加入事件处理器链条中。做抽象类用
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-19:59
 */
public class EventHandler extends AbstractMessageHandler {


    /**
     * 事件处理器的模板方法
     *
     * @param baseMessageRequest 处理器
     * @return 消息
     */
    @Override
    public final BaseMessage handleMessage(BaseMessage baseMessageRequest, Element element) {
        BaseMessage baseMessage = null;
        BaseEventRequest baseEventRequest = null;
        try {
            baseEventRequest = XmlToObjectUtil.xmlToObject(element, BaseEventRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        if (this.getHandlerLevel().equals(baseEventRequest.getEvent())) {
            baseMessage = this.dealTask(element);
        } else {
            // todo do something
        }
        return baseMessage;
    }

    @Override
    protected String getHandlerLevel() {
        return null;
    }

    @Override
    protected BaseMessage dealTask(Element element) {
        return null;
    }

}

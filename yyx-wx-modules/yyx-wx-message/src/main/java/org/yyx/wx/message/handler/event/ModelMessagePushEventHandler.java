package org.yyx.wx.message.handler.event;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.bussinessenum.EventTypeEnum;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.request.event.ModelMessagePushEventRequest;
import org.yyx.wx.commons.vo.pubnum.response.message.BaseMessageResponse;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;
import org.yyx.wx.message.proxy.event.ModelMessagePushEventHandlerProxy;


/**
 * 模板消息推送事件处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/1-10:40
 */
public class ModelMessagePushEventHandler extends BaseSubscribeEventHandler {

    /**
     * ModelMessagePushEventHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelMessagePushEventHandler.class);
    private static final ModelMessagePushEventHandler MODEL_MESSAGE_PUSH_EVENT_HANDLER = new ModelMessagePushEventHandler();

    private ModelMessagePushEventHandler() {
    }

    public static ModelMessagePushEventHandler getInstance() {
        return MODEL_MESSAGE_PUSH_EVENT_HANDLER;
    }

    /**
     * 模板推送事件处理器
     *
     * @param element 实际处理器要处理的数据
     * @return null
     */
    @Override
    protected BaseMessageResponse dealTask(Element element) {
        LOGGER.info("[模板消息推送]事件处理器");
        ModelMessagePushEventRequest modelMessagePushEventRequest = this.modelMethod(element);
        return baseMessageHandlerProxy.dealMessage(modelMessagePushEventRequest);
    }

    @Override
    protected String getHandlerLevel() {
        return EventTypeEnum.TEMPLATESENDJOBFINISH.toString();
    }

    @Override
    protected ModelMessagePushEventRequest modelMethod(Element element) {
        LOGGER.info("[微信请求过来的消息:xml格式数据] {}", element);
        ModelMessagePushEventRequest modelMessagePushEventRequest;
        try {
            modelMessagePushEventRequest = WxXmlAndObjectUtil.xmlToObject(element, ModelMessagePushEventRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        return modelMessagePushEventRequest;
    }

    @Override
    protected boolean isMineProxy(BaseMessageHandlerProxy baseMessageHandlerProxy) {
        if (baseMessageHandlerProxy instanceof ModelMessagePushEventHandlerProxy) {
            this.baseMessageHandlerProxy = baseMessageHandlerProxy;
            return true;
        }
        return false;
    }
}

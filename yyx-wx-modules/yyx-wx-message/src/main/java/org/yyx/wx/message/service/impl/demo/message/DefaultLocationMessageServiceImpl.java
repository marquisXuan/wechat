package org.yyx.wx.message.service.impl.demo.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.vo.pubnum.response.message.BaseMessageResponse;
import org.yyx.wx.commons.vo.pubnum.response.message.TextMessageResponse;
import org.yyx.wx.commons.vo.pubnum.BaseMessageAndEventRequestAndResponse;
import org.yyx.wx.commons.vo.pubnum.request.message.LocationMessageRequest;
import org.yyx.wx.message.proxy.message.LocationMessageHandlerProxy;

/**
 * 自定义地理位置业务实现类 - DEMO
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/10-13:39
 */
@Service
public class DefaultLocationMessageServiceImpl implements LocationMessageHandlerProxy {

    /**
     * DemoServiceImpl日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLocationMessageServiceImpl.class);

    /**
     * 自定义业务处理
     *
     * @param baseMessageAndEventRequest 微信推送过来的事件实体
     * @return 返回消息
     */
    @Override
    public BaseMessageResponse dealMessage(BaseMessageAndEventRequestAndResponse baseMessageAndEventRequest) {
        LocationMessageRequest locationMessageRequest = (LocationMessageRequest) baseMessageAndEventRequest;
        LOGGER.info("[DEMO] 自定义地理位置业务实现类");
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        textMessageResponse.setMsgId(1L);
        textMessageResponse.setToUserName(baseMessageAndEventRequest.getFromUserName());
        textMessageResponse.setFromUserName(baseMessageAndEventRequest.getToUserName());
        textMessageResponse.setMsgType(MessageTypeEnum.text.toString());
        textMessageResponse.setContent("[DEMO] 叶云轩自定义文本回复。\n你发的是一条地理位置消息：" +
                locationMessageRequest.getLabel() + "经纬度为：" + locationMessageRequest.getLocation_X() + ":" + locationMessageRequest.getLocation_Y());
        // endregion
        return textMessageResponse;
    }
}
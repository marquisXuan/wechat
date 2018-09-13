package org.yyx.wx.serviceimpl;

import org.springframework.stereotype.Service;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.vo.pubnum.reponse.message.BaseMessageResponse;
import org.yyx.wx.commons.vo.pubnum.reponse.message.TextMessageResponse;
import org.yyx.wx.commons.vo.pubnum.request.BaseMessageAndEventRequestAndResponse;
import org.yyx.wx.commons.vo.pubnum.request.message.TextMessageRequest;
import org.yyx.wx.message.proxy.message.TextMessageHandlerProxy;

/**
 * 接收到文本类消息时所做的处理
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/10-18:40
 */
@Service
public class TextMessageServiceImpl implements TextMessageHandlerProxy {
    /**
     * 接收到文本类消息时所做的处理逻辑
     *
     * @param baseMessageAndEventRequest 微信推送过来的事件实体
     * @return 返回给用户的信息
     */
    @Override
    public BaseMessageResponse dealMessage(BaseMessageAndEventRequestAndResponse baseMessageAndEventRequest) {
        TextMessageRequest textMessageRequest = (TextMessageRequest) baseMessageAndEventRequest;
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        textMessageResponse.setMsgId(1L);
        textMessageResponse.setToUserName(baseMessageAndEventRequest.getFromUserName());
        textMessageResponse.setFromUserName(baseMessageAndEventRequest.getToUserName());
        textMessageResponse.setMsgType(MessageTypeEnum.text.toString());
        textMessageResponse.setContent("[DEMO] 这是我的自定义文本消息推送业务逻辑代码：" + textMessageRequest.getContent());
        // endregion
        return textMessageResponse;
    }
}

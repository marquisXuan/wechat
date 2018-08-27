package org.yyx.wx.message.handler.message;

import org.dom4j.Element;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.util.XmlToObjectUtil;
import org.yyx.wx.commons.vo.pubnum.reponse.message.TextMessageResponse;
import org.yyx.wx.commons.vo.pubnum.request.message.TextMessageRequest;
import org.yyx.wx.message.handler.AbstractMessageHandler;

/**
 * 文本消息处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-19:45
 */
public class TextMessageHandler extends AbstractMessageHandler {
    /**
     * 获取文本消息处理器级别
     *
     * @return 文本消息处理器级别
     */
    @Override
    protected String getHandlerLevel() {
        return MessageTypeEnum.text.toString();
    }

    /**
     * 文本消息处理器
     *
     * @param element 实际处理器要处理的数据
     * @return 处理后的消息
     */
    @Override
    protected TextMessageResponse dealTask(Element element) {
        // 强转成文本消息
        TextMessageRequest textMessageRequest;
        try {
            textMessageRequest = XmlToObjectUtil.xmlToObject(element, TextMessageRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        // 封装返回消息实体
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        // 发送者是请求者对象中的接收者
        textMessageResponse.setFromUserName(textMessageRequest.getToUserName());
        // 接收者是请求者对象中的发送者
        textMessageResponse.setToUserName(textMessageRequest.getFromUserName());
        textMessageResponse.setContent("我是叶云轩的服务器，我返回的内容是：[你发的消息内容是]"
                + textMessageRequest.getContent() + "\n 今后补写富文本消息，<a href='https://my.oschina.net/yzwjyw/'>我的博客</a>");
        textMessageResponse.setMsgId(textMessageRequest.getMsgId());
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        return textMessageResponse;
    }
}

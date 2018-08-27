package org.yyx.wx.message.handler.event;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.bussinessenum.EventTypeEnum;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.util.XmlToObjectUtil;
import org.yyx.wx.commons.vo.pubnum.BaseMessage;
import org.yyx.wx.commons.vo.pubnum.reponse.message.TextMessageResponse;
import org.yyx.wx.commons.vo.pubnum.request.event.SubscribeAndUnSubscribeScanEventRequest;
import org.yyx.wx.message.websocket.WebSocketUtil;

import java.io.IOException;


/**
 * 扫描二维码事件处理器
 * 用户未关注时，进行关注后的事件推送
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-20:02
 */
public class ScanSubscribeEventHandler extends EventHandler {

    /**
     * SubscribeEventHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ScanSubscribeEventHandler.class);

    /**
     * 设置处理级别为订阅事件
     *
     * @return 处理级别
     */
    @Override
    protected String getHandlerLevel() {
        return EventTypeEnum.subscribe.toString();
    }

    /**
     * 实际处理任务
     *
     * @param element 实际处理器要处理的数据
     * @return 处理后封装的消息
     */
    @Override
    protected BaseMessage dealTask(Element element) {
        LOGGER.info("用户未关注时，扫描二维码事件处理器开始工作]");
        SubscribeAndUnSubscribeScanEventRequest subscribeAndUnSubscribeScanEventRequest;
        try {
            subscribeAndUnSubscribeScanEventRequest =
                    XmlToObjectUtil.xmlToObject(element, SubscribeAndUnSubscribeScanEventRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        LOGGER.info("[推送详情] {}", subscribeAndUnSubscribeScanEventRequest);
        String userName = subscribeAndUnSubscribeScanEventRequest.getEventKey().substring(8);
        try {
            WebSocketUtil.sendMessageToUser(userName, "_redirect");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 订阅后发送欢迎语
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        textMessageResponse.setMsgId(1);
        textMessageResponse.setToUserName(subscribeAndUnSubscribeScanEventRequest.getFromUserName());
        textMessageResponse.setFromUserName(subscribeAndUnSubscribeScanEventRequest.getToUserName());
        textMessageResponse.setMsgType(MessageTypeEnum.text.toString());
        textMessageResponse.setContent("欢迎使用 [叶云轩公众号测试号] ");
        return textMessageResponse;
    }
}

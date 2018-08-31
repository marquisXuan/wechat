package org.yyx.wx.message.handler.event;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.bussinessenum.EventTypeEnum;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.BaseMessageAndEvent;
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
public class SubscribeScanEventHandler extends BaseSubscribeEventHandler {

    /**
     * SubscribeEventHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscribeScanEventHandler.class);

    /**
     * 用户扫描生成的二维码时，进入的业务处理
     *
     * @param element 实际处理器要处理的数据
     * @return 处理后封装的消息
     */
    @Override
    protected BaseMessageAndEvent dealTask(Element element) {
        LOGGER.info("进入用户未关注时，进入扫描二维码事件处理器]");
        SubscribeAndUnSubscribeScanEventRequest subscribeAndUnSubscribeScanEventRequest;
        try {
            // xml数据转换成扫描带参数二维码事件请求实体对象
            subscribeAndUnSubscribeScanEventRequest =
                    WxXmlAndObjectUtil.xmlToObject(element, SubscribeAndUnSubscribeScanEventRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        LOGGER.info("[扫描带参数二维码事件请求详情] {}", subscribeAndUnSubscribeScanEventRequest);
        // region 业务逻辑
        String userName = subscribeAndUnSubscribeScanEventRequest.getEventKey().substring(8);
        // 用户标识信息
        String fromUserName = subscribeAndUnSubscribeScanEventRequest.getFromUserName();
        LOGGER.info("[fromUserName] {}", fromUserName);
        try {
            // 用户openId保存在Redis中。
            redisTemplate.opsForValue().set("user_" + userName, fromUserName);
            WebSocketUtil.sendMessageToUser(userName, "_redirect");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 订阅后发送欢迎语
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        textMessageResponse.setMsgId(1);
        textMessageResponse.setToUserName(fromUserName);
        textMessageResponse.setFromUserName(subscribeAndUnSubscribeScanEventRequest.getToUserName());
        textMessageResponse.setMsgType(MessageTypeEnum.text.toString());
        textMessageResponse.setContent("欢迎使用 [叶云轩公众号测试号] ");
        // endregion
        return textMessageResponse;
    }

    /**
     * 设置处理级别为订阅事件
     *
     * @return 处理级别
     */
    @Override
    protected String getHandlerLevel() {
        return EventTypeEnum.qrscene_.toString();
    }
}

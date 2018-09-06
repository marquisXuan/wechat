package org.yyx.wx.message.handler.event;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.reponse.message.BaseMessageResponse;
import org.yyx.wx.commons.vo.pubnum.request.event.SubscribeAndUnSubscribeEventRequest;


/**
 * 订阅[关注]事件处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-20:02
 */
public class SubscribeEventHandler extends BaseSubscribeEventHandler {

    /**
     * SubscribeEventHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscribeEventHandler.class);
    /**
     * 创建对象
     */
    private static final SubscribeEventHandler SUBSCRIBE_EVENT_HANDLER = new SubscribeEventHandler();

    /**
     * 私有构造
     */
    private SubscribeEventHandler() {
    }

    /**
     * 获取对象
     *
     * @return 返回对象
     */
    public static SubscribeEventHandler getInstance() {
        return SUBSCRIBE_EVENT_HANDLER;
    }

    /**
     * 实际处理任务
     *
     * @param element 实际处理器要处理的数据
     * @return 处理后封装的消息
     */
    @Override
    protected BaseMessageResponse dealTask(Element element) {
        LOGGER.info("[订阅[关注]事件处理器]");
        SubscribeAndUnSubscribeEventRequest subscribeAndUnSubscribeEventRequest = this.modelMethod(element);
        return iMessageHandler.dealMessage(subscribeAndUnSubscribeEventRequest);
    }

    /**
     * 设置处理级别为订阅事件
     *
     * @return 处理级别
     */
    @Override
    protected String getHandlerLevel() {
        return null;
    }

    @Override
    protected SubscribeAndUnSubscribeEventRequest modelMethod(Element element) {
        LOGGER.info("[微信请求过来的消息:xml格式数据] {}", element);
        SubscribeAndUnSubscribeEventRequest subscribeAndUnSubscribeEventRequest;
        try {
            subscribeAndUnSubscribeEventRequest
                    = WxXmlAndObjectUtil.xmlToObject(element, SubscribeAndUnSubscribeEventRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        return subscribeAndUnSubscribeEventRequest;
    }
}

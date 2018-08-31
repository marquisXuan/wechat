package org.yyx.wx.message.handler.event;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.bussinessenum.EventTypeEnum;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.BaseMessageAndEvent;
import org.yyx.wx.commons.vo.pubnum.request.event.SubscribeAndUnSubscribeEventRequest;


/**
 * 取消关注公众号事件处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-20:02
 */
public class UnSubscribeEventHandler extends BaseEventHandler {

    /**
     * SubscribeEventHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UnSubscribeEventHandler.class);

    /**
     * 实际处理任务
     *
     * @param element 实际处理器要处理的数据
     * @return 处理后封装的消息
     */
    @Override
    protected BaseMessageAndEvent dealTask(Element element) {
        LOGGER.info("[取消订阅[关注]事件处理器]");
        // region 业务逻辑
        SubscribeAndUnSubscribeEventRequest subscribeAndUnSubscribeEventRequest;
        try {
            // todo 取消公众号时的逻辑
            subscribeAndUnSubscribeEventRequest
                    = WxXmlAndObjectUtil.xmlToObject(element, SubscribeAndUnSubscribeEventRequest.class);
            LOGGER.info("[用户取消关注公众号] {}", subscribeAndUnSubscribeEventRequest);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        // endregion
        return null;
    }

    /**
     * 设置处理级别为订阅事件
     *
     * @return 处理级别
     */
    @Override
    protected String getHandlerLevel() {
        return EventTypeEnum.unsubscribe.toString();
    }
}

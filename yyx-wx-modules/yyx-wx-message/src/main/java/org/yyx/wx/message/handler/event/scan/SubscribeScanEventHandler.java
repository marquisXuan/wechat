package org.yyx.wx.message.handler.event.scan;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.bussinessenum.EventTypeEnum;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.request.event.SubscribeAndUnSubscribeScanEventRequest;
import org.yyx.wx.commons.vo.pubnum.response.message.BaseMessageResponse;
import org.yyx.wx.message.handler.event.BaseEventHandler;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;
import org.yyx.wx.message.proxy.event.SubscribeScanEventHandlerProxy;


/**
 * 用户已关注时扫描二维码事件处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-20:02
 */
public class SubscribeScanEventHandler extends BaseEventHandler {
    /**
     * SubscribeEventHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscribeScanEventHandler.class);
    /**
     * 创建对象
     */
    private static final SubscribeScanEventHandler SUBSCRIBE_SCAN_EVENT_HANDLER = new SubscribeScanEventHandler();

    /**
     * 私有构造
     */
    private SubscribeScanEventHandler() {
    }

    /**
     * 获取对象
     *
     * @return 返回对象
     */
    public static SubscribeScanEventHandler getInstance() {
        return SUBSCRIBE_SCAN_EVENT_HANDLER;
    }

    /**
     * 用户扫描生成的二维码时，进入的业务处理
     *
     * @param element 实际处理器要处理的数据
     * @return 处理后封装的消息
     */
    @Override
    protected BaseMessageResponse dealTask(Element element) {
        LOGGER.info("进入用户关注时，进入扫描二维码事件处理器]");
        SubscribeAndUnSubscribeScanEventRequest subscribeAndUnSubscribeScanEventRequest = this.modelMethod(element);
        LOGGER.info("[扫描带参数二维码事件请求详情] {}", subscribeAndUnSubscribeScanEventRequest);
        return baseMessageHandlerProxy.dealMessage(subscribeAndUnSubscribeScanEventRequest);
    }

    /**
     * 设置处理级别为订阅事件
     *
     * @return 处理级别
     */
    @Override
    protected String getHandlerLevel() {
        return EventTypeEnum.SCAN.toString();
    }

    @Override
    protected SubscribeAndUnSubscribeScanEventRequest modelMethod(Element element) {
        LOGGER.info("[微信请求过来的消息:xml格式数据] {}", element);
        SubscribeAndUnSubscribeScanEventRequest subscribeAndUnSubscribeScanEventRequest;
        try {
            subscribeAndUnSubscribeScanEventRequest
                    = WxXmlAndObjectUtil.xmlToObject(element, SubscribeAndUnSubscribeScanEventRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        return subscribeAndUnSubscribeScanEventRequest;
    }

    /**
     * 检查是否是自己的代理类
     *
     * @return true / false
     */
    @Override
    protected boolean isMineProxy(BaseMessageHandlerProxy baseMessageHandlerProxy) {
        if (baseMessageHandlerProxy instanceof SubscribeScanEventHandlerProxy) {
            this.baseMessageHandlerProxy = baseMessageHandlerProxy;
            return true;
        }
        return false;
    }
}

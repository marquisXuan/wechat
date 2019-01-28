package org.yyx.wx.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.util.CacheService;
import org.yyx.wx.commons.vo.pubnum.BaseMessageAndEventRequestAndResponse;
import org.yyx.wx.commons.vo.pubnum.response.message.BaseMessageResponse;
import org.yyx.wx.commons.vo.pubnum.response.message.TextMessageResponse;
import org.yyx.wx.message.proxy.event.SubscribeEventHandlerProxy;

import javax.annotation.Resource;

/**
 * Demo
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019-01-28-13:00
 */
@Service
public class CustomSubscribeEventHandler implements SubscribeEventHandlerProxy {
    /**
     * CustomSubscribeEventHandler 日志输出
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomSubscribeEventHandler.class);
    @Resource
    private CacheService<String, Integer> cacheService;

    @Override
    public BaseMessageResponse dealMessage(BaseMessageAndEventRequestAndResponse baseMessageAndEventRequest) {
        cacheService.print();
        String fromUserName = baseMessageAndEventRequest.getFromUserName();
        LOGGER.info("[dealMessage] -> [来自用户] {}", fromUserName);
        Integer value = cacheService.getValue(fromUserName);
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        int count = 0;
        textMessageResponse.setContent("新用户：<a href='https://www.lenogoimage.com'>联想图像官网</a>");
        if (value != null) {
            LOGGER.info("[dealMessage] -> [老用户] {}", value);
            count = value + 1;
            textMessageResponse.setContent("老用户：<a href='http://www.dayin8.com/printer_manage/login.html'>打印吧官网</a>");
        }
        cacheService.cacheValue(fromUserName, count);
        cacheService.print();
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        textMessageResponse.setFromUserName(baseMessageAndEventRequest.getToUserName());
        textMessageResponse.setToUserName(fromUserName);
        textMessageResponse.setMsgId(1);
        textMessageResponse.setMsgType(MessageTypeEnum.text.toString());
        return textMessageResponse;
    }
}

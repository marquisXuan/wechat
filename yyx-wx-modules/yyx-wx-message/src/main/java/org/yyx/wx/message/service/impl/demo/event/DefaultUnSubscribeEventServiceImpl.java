package org.yyx.wx.message.service.impl.demo.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yyx.wx.commons.vo.pubnum.BaseMessageAndEventRequestAndResponse;
import org.yyx.wx.commons.vo.pubnum.request.event.SubscribeAndUnSubscribeEventRequest;
import org.yyx.wx.commons.vo.pubnum.request.user.WxUserInfoRequest;
import org.yyx.wx.commons.vo.pubnum.response.message.BaseMessageResponse;
import org.yyx.wx.message.proxy.event.UnSubscribeEventHandlerProxy;
import org.yyx.wx.user.service.IUserInfoService;

import javax.annotation.Resource;

/**
 * 自定义取消关注业务实现类 - DEMO
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/10-13:39
 */
@Service
public class DefaultUnSubscribeEventServiceImpl implements UnSubscribeEventHandlerProxy {

    /**
     * DemoServiceImpl日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUnSubscribeEventServiceImpl.class);

    /**
     * 封装的用户相关的业务接口
     */
    @Resource
    private IUserInfoService userInfoService;

    /**
     * 自定义业务处理
     *
     * @param baseMessageAndEventRequest 微信推送过来的事件实体
     * @return 返回消息
     */
    @Override
    public final BaseMessageResponse dealMessage(BaseMessageAndEventRequestAndResponse baseMessageAndEventRequest) {
        SubscribeAndUnSubscribeEventRequest subscribeAndUnSubscribeEventRequest = (SubscribeAndUnSubscribeEventRequest) baseMessageAndEventRequest;
        String openID = subscribeAndUnSubscribeEventRequest.getFromUserName();
        WxUserInfoRequest userInfoByOpenID = userInfoService.getUserInfoByOpenID(openID);
        LOGGER.info("[用户{}取消了关注] {}", userInfoByOpenID.getNickname(), userInfoByOpenID);
        return null;
    }
}
package org.yyx.wx.message.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yyx.wx.acount.auth.service.IAccessTokenService;
import org.yyx.wx.commons.vo.pubnum.request.auth.BaseAccessTokenRequest;
import org.yyx.wx.commons.vo.pubnum.response.model.ModelMessageResponse;
import org.yyx.wx.message.config.WxModelMessageConfig;
import org.yyx.wx.message.service.IMessageService;

import javax.annotation.Resource;

/**
 * 消息Service实现
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/1-09:16
 */
@Service
public class MessageServiceImpl implements IMessageService {

    /**
     * MessageServiceImpl日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
    /**
     * 微信模板消息相关的配置类
     */
    @Resource
    private WxModelMessageConfig wxModelMessageConfig;
    /**
     * AccessToken相关服务
     */
    @Resource
    private IAccessTokenService accessTokenService;

    /**
     * 推送模板消息业务
     *
     * @param modelMessageResponse 封装的模板消息
     * @return 推送状态是否成功（不代表一定推送成功，只有接收到微信回调才肯定推送成功）
     */
    @Override
    public boolean pushModelService(ModelMessageResponse modelMessageResponse) {
        BaseAccessTokenRequest baseAccessTokenRequest = accessTokenService.getBaseAccessToken();
        if (baseAccessTokenRequest == null) {
            // 报错
            return false;
        }
        // POST请求URL
        String urlSendModelMessage = wxModelMessageConfig.getUrlSendModelMessage()
                + baseAccessTokenRequest.getAccess_token();
        String modelMessageJson = JSONObject.toJSONString(modelMessageResponse);
        LOGGER.info("[推送消息] {}", modelMessageJson);
        String post = HttpUtil.post(urlSendModelMessage, modelMessageJson);
        JSONObject jsonObject = JSONObject.parseObject(post);
        int errcode = (int) jsonObject.get("errcode");
        // success
        return errcode == 0;
    }
}

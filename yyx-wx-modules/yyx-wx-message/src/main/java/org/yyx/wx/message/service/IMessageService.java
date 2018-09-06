package org.yyx.wx.message.service;


import org.yyx.wx.commons.vo.pubnum.reponse.model.ModelMessageResponse;

/**
 * 消息Service
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/1-09:16
 */
public interface IMessageService {
    /**
     * 推送模板消息业务
     *
     * @param modelMessageResponse 封装的模板消息
     * @return 推送状态是否成功（不代表一定推送成功，只有接收到微信回调才肯定推送成功）
     */
    boolean pushModelService(ModelMessageResponse modelMessageResponse);
}

package org.yyx.wx.commons.vo.pubnum.request.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模板消息推送事件
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/1-10:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModelMessagePushEventRequest extends BaseEventRequest {

    /**
     * 消息id
     */
    private String MsgID;
    /**
     * 发送状态为成功
     */
    private String Status;
}
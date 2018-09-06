package org.yyx.wx.commons.vo.pubnum.reponse.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yyx.wx.commons.vo.pubnum.request.BaseMessageAndEventRequestAndResponse;

/**
 * 基础消息类
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 16:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseMessageResponse extends BaseMessageAndEventRequestAndResponse {

    /**
     * 消息id，64位整型
     */
    private long MsgId;
}

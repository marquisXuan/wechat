package org.yyx.wx.commons.vo.pubnum.request.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yyx.wx.commons.vo.pubnum.request.BaseMessageAndEventRequestAndResponse;

/**
 * 微信请求过来的基础消息父类
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 16:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseMessageRequest extends BaseMessageAndEventRequestAndResponse {
    /**
     * 消息id，64位整型
     */
    private long MsgId;
}

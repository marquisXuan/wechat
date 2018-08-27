package org.yyx.wx.commons.vo.pubnum.request.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yyx.wx.commons.vo.pubnum.BaseMessage;

/**
 * 基础事件
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-20:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEventRequest extends BaseMessage {

    /**
     * 事件类型
     */
    private String Event;

}

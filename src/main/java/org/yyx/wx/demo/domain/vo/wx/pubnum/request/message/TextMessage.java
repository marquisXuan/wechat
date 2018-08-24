package org.yyx.wx.demo.domain.vo.wx.pubnum.request.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 普通文本消息类
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 16:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TextMessage extends BaseMessage {

    /**
     * 文本消息内容
     */
    private String Content;
}

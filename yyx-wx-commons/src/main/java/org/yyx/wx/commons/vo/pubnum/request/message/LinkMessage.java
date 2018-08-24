package org.yyx.wx.commons.vo.pubnum.request.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 链接消息
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 16:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LinkMessage extends BaseMessage {

    /**
     * 消息标题
     */
    private String Title;

    /**
     * 消息描述
     */
    private String Description;

    /**
     * 消息链接
     */
    private String Url;
}
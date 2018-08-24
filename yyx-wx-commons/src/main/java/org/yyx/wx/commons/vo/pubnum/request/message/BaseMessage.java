package org.yyx.wx.commons.vo.pubnum.request.message;

import lombok.Data;

/**
 * 基础消息类
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 16:28
 */
@Data
public class BaseMessage {
    /**
     * 开发者微信号
     */
    private String ToUserName;
    /**
     * 消息发送者账号（OPENID）
     */
    private String FromUserName;
    /**
     * 消息创建时间
     */
    private long CreateTime;
    /**
     * 消息类型
     */
    private String MsgType;
    /**
     * 消息id，64位整型
     */
    private long MsgId;
}

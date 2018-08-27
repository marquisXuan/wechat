package org.yyx.wx.commons.vo.pubnum;

import lombok.Data;

/**
 * 基础消息抽象类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-10:21
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
}

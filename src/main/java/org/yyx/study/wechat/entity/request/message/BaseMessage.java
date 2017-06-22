package org.yyx.study.wechat.entity.request.message;

/**
 * 基础消息类
 * Created by 叶云轩 on 2017/6/21-11:14
 * Concat ycountjavaxuan@outlook.com
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseMessage that = (BaseMessage) o;

        if (CreateTime != that.CreateTime) return false;
        if (MsgId != that.MsgId) return false;
        if (ToUserName != null ? !ToUserName.equals(that.ToUserName) : that.ToUserName != null) return false;
        if (FromUserName != null ? !FromUserName.equals(that.FromUserName) : that.FromUserName != null) return false;
        return MsgType != null ? MsgType.equals(that.MsgType) : that.MsgType == null;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    @Override
    public int hashCode() {
        int result = ToUserName != null ? ToUserName.hashCode() : 0;
        result = 31 * result + (FromUserName != null ? FromUserName.hashCode() : 0);
        result = 31 * result + (int) (CreateTime ^ (CreateTime >>> 32));
        result = 31 * result + (MsgType != null ? MsgType.hashCode() : 0);
        result = 31 * result + (int) (MsgId ^ (MsgId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                ", MsgId=" + MsgId +
                '}';
    }
}

package org.yyx.wx.commons.bussinessenum;

/**
 * 消息类型枚举类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-10:37
 */
public enum MessageTypeEnum {

    /**
     * 文本消息
     */
    text("文本消息"),
    /**
     * 图片消息
     */
    image("图片消息"),
    /**
     * 语音消息
     */
    voice("语音消息"),
    /**
     * 视频消息
     */
    video("视频消息"),
    /**
     * 小视频消息
     */
    shortvideo("小视频消息"),
    /**
     * 地理位置消息
     */
    location("地理位置消息"),
    /**
     * 链接消息
     */
    link("链接消息"),
    /**
     * 事件
     */
    event("事件"),

    ;

    /**
     * 描述信息
     */
    private String desc;

    MessageTypeEnum(String desc) {
        this.desc = desc;
    }

    /**
     * 获取描述信息
     *
     * @param code 响应码
     * @return 描述信息
     */
    public static String getDesc(String code) {
        MessageTypeEnum[] values = MessageTypeEnum.values();
        for (MessageTypeEnum value : values) {
            if (value.toString().equals(code)) {
                return value.desc;
            }
        }
        return null;
    }
}

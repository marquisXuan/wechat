package org.yyx.wx.commons.bussinessenum;

/**
 * 事件类型枚举类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-20:36
 */
public enum EventTypeEnum {

    /**
     * 关注/取消关注事件，subscribe(订阅)
     * 扫描带参数二维码事件,用户未关注时，进行关注后的事件推送
     */
    subscribe,
    /**
     * 关注/取消关注事件,unsubscribe(取消订阅)
     */
    unsubscribe,
    /**
     * 扫描带参数二维码事件,用户已关注时的事件推送
     */
    SCAN,
    /**
     * 上报地理位置事件
     */
    LOCATION,
    /**
     * 自定义菜单事件,点击菜单拉取消息时的事件推送
     */
    CLICK,
    /**
     * 自定义菜单事件,点击菜单跳转链接时的事件推送
     */
    VIEW
}

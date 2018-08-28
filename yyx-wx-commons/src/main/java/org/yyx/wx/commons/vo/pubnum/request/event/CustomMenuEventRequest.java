package org.yyx.wx.commons.vo.pubnum.request.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义菜单事件请求
 * <p>
 * 点击菜单拉取消息时的事件推送：Event为CLICK，EventKey为事件KEY值，与自定义菜单接口中KEY值对应
 * 点击菜单跳转链接时的事件推送：Event为VIEW，EventKey	事件KEY值，设置的跳转URL
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/27-14:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomMenuEventRequest extends BaseEventRequest {
    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    private String EventKey;

}
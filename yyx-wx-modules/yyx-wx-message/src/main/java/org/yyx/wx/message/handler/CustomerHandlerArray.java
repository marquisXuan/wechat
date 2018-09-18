package org.yyx.wx.message.handler;

import static org.yyx.wx.commons.constant.HandlerConstant.MAX_HANDLER_COUNT;

/**
 * 自定义消息处理器数组
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/18-10:30
 */
public class CustomerHandlerArray {

    /**
     * 创建一个数组用于存放自定义消息处理链
     */
    public static final AbstractMessageHandler[] ABSTRACT_MESSAGE_HANDLERS = new AbstractMessageHandler[MAX_HANDLER_COUNT];
}

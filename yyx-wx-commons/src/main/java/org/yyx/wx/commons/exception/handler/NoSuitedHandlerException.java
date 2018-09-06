package org.yyx.wx.commons.exception.handler;

/**
 * 没有合适的处理器异常信息
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/6-13:59
 */
public class NoSuitedHandlerException extends HandlerException {
    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 3933411208644624163L;

    public NoSuitedHandlerException() {
        super("没有可以处理该类型事件的处理器");
    }

    public NoSuitedHandlerException(String message) {
        super(message);
    }
}

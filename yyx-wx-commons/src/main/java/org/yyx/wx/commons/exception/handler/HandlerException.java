package org.yyx.wx.commons.exception.handler;

/**
 * 处理器异常
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/6-13:58
 */
public class HandlerException extends RuntimeException {
    /**
     * 序列化标识
     */
    private static final long serialVersionUID = -71658612538884383L;

    public HandlerException(String message) {
        super(message);
    }

    public HandlerException() {
        super();
    }
}

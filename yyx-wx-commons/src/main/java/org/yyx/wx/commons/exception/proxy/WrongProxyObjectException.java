package org.yyx.wx.commons.exception.proxy;

/**
 * 错误的代理对象
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/6-16:56
 */
public class WrongProxyObjectException extends ProxyException {
    private static final long serialVersionUID = -3113087570922724108L;

    public WrongProxyObjectException() {
        super("使用的错误的代理对象");
    }

    public WrongProxyObjectException(String message) {
        super(message);
    }
}

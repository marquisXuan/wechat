package org.yyx.wx.commons.exception.proxy;

/**
 * 代理异常父类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/6-16:55
 */
public class ProxyException extends RuntimeException {

    private static final long serialVersionUID = -3571080044547382545L;

    public ProxyException() {
        super();
    }

    public ProxyException(String message) {
        super(message);
    }
}

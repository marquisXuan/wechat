package org.yyx.wx.commons.exception;

/**
 * 转换异常
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/3-14:29
 */
public class TranslateException extends RuntimeException {
    private static final long serialVersionUID = 2694110035564656389L;

    public TranslateException(String message) {
        super(message);
    }
}

package org.yyx.wx.commons.exception;

/**
 * 创建对象异常
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/3-11:50
 */
public class CreateObjectException extends RuntimeException {
    private static final long serialVersionUID = 6740576816071113787L;

    public CreateObjectException(String message) {
        super(message);
    }
}

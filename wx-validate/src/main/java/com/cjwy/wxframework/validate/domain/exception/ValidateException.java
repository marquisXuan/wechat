package com.cjwy.wxframework.validate.domain.exception;

/**
 * 验证异常
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/25 7:08 上午
 */
public class ValidateException extends RuntimeException {

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException() {
        super();
    }
}

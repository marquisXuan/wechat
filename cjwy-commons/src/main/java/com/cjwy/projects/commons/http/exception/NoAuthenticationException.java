package com.cjwy.projects.commons.http.exception;

import com.cjwy.projects.commons.http.domain.enumm.ApiResponseEnum;

/**
 * 没有权限访问异常
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/31 3:08 上午
 */
public class NoAuthenticationException extends BusinessException {

    public NoAuthenticationException(ApiResponseEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}

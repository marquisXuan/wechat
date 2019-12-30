package com.cjwy.projects.commons.http.exception;

import com.cjwy.projects.commons.http.domain.enumm.ApiResponseEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/31 4:18 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private String code;

    public BusinessException(ApiResponseEnum apiResponseEnum) {
        super(apiResponseEnum.getMsg());
        this.code = apiResponseEnum.getCode();
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

}

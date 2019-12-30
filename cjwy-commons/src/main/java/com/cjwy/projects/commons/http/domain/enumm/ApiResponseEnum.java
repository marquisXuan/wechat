package com.cjwy.projects.commons.http.domain.enumm;

import lombok.Getter;
import lombok.Setter;

/**
 * api 响应枚举
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 9:03 下午
 */
public enum ApiResponseEnum implements ApiResponseEnumInterface {

    /**
     * 响应成功时的响应
     */
    success("200000200", "request success"),
    /**
     * 生成业务 Token 错误时返回的响应
     */
    authentication_token_generate_error("500001001", "generate token exception"),
    authentication_no("401001001", "no authentication"),
    /**
     * 服务器返回错误的响应
     */
    error("500500500", "server error");

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String msg;

    /**
     * 响应
     *
     * @param code 响应码
     * @param msg  响应码说明
     */
    ApiResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

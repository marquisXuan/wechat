package com.cjwy.projects.commons.domain.vo;

import lombok.Data;

/**
 * api 响应实体类 VO
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 8:50 下午
 */
@Data
public class ApiResponseVO<T> {
    /**
     * 响应码
     */
    private String code;
    /**
     * 响应码说明
     */
    private String msg;
    /**
     * 数据
     */
    private T data;
}

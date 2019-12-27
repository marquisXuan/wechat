package com.cjwy.projects.commons.domain.enumm;

/**
 * api 响应枚举接口
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 8:53 下午
 */
public interface ApiResponseEnumInterface {

    /**
     * 响应码
     *
     * @return 获取响应码
     */
    String getCode();

    /**
     * 获取响应码说明
     *
     * @return 响应码说明
     */
    String getMsg();
}

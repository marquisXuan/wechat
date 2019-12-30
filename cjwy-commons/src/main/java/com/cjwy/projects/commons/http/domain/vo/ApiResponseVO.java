package com.cjwy.projects.commons.http.domain.vo;

import com.cjwy.projects.commons.http.domain.enumm.ApiResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * api 响应实体类 VO
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 8:50 下午
 */
@Data
@ApiModel(value = "响应实体类", description = "封装的同样风格的响应结构")
public class ApiResponseVO<T> {
    /**
     * 响应码
     */
    @ApiModelProperty(value = "响应码", name = "code")
    private String code;
    /**
     * 响应码说明
     */
    @ApiModelProperty(value = "响应码说明", name = "msg")
    private String msg;
    /**
     * 数据
     */
    @ApiModelProperty(value = "响应时携带的数据", name = "data")
    private T data;

    /**
     * 响应是否失败
     *
     * @return true 失败 false 成功
     */
    public boolean isFail() {
        return !isSuccess();
    }

    /**
     * 响应是否成功
     *
     * @return true 成功 false 失败
     */
    public boolean isSuccess() {
        return ApiResponseEnum.success.getCode().equals(this.code);
    }
}

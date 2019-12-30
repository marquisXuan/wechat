package com.cjwy.projects.commons.http.utils;

import com.cjwy.projects.commons.http.domain.enumm.ApiResponseEnum;
import com.cjwy.projects.commons.http.domain.enumm.ApiResponseEnumInterface;
import com.cjwy.projects.commons.http.domain.vo.ApiResponseVO;

/**
 * 响应工具类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 9:06 下午
 */
public class UtilApiResponse<T> {

    private UtilApiResponse() {
    }

    /**
     * 响应成功,且有数据时的方法
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 响应结果
     */
    public static <T> ApiResponseVO<T> success(T data) {
        ApiResponseVO<T> apiResponseVO = new ApiResponseVO<>();
        apiResponseVO.setData(data);
        apiResponseVO.setCode(ApiResponseEnum.success.getCode());
        apiResponseVO.setMsg(ApiResponseEnum.success.getMsg());
        return apiResponseVO;
    }

    /**
     * 响应成功,没有数据时的方法
     *
     * @param <Void> Void类型
     * @return 响应结果
     */
    public static <Void> ApiResponseVO<Void> success() {
        ApiResponseVO<Void> apiResponseVO = new ApiResponseVO<>();
        apiResponseVO.setData(null);
        apiResponseVO.setCode(ApiResponseEnum.success.getCode());
        apiResponseVO.setMsg(ApiResponseEnum.success.getMsg());
        return apiResponseVO;
    }

    /**
     * 自定义的响应结果封闭
     *
     * @param apiResponseEnumInterface 接口
     * @param <T>                      返回的数据类型
     * @param data                     数据
     * @return 返回结果
     */
    public static <T> ApiResponseVO<T> success(ApiResponseEnumInterface apiResponseEnumInterface, T data) {
        ApiResponseVO<T> apiResponseVO = new ApiResponseVO<>();
        apiResponseVO.setData(data);
        apiResponseVO.setCode(apiResponseEnumInterface.getCode());
        apiResponseVO.setMsg(apiResponseEnumInterface.getMsg());
        return apiResponseVO;
    }

    /**
     * 自定义的响应结果封闭
     *
     * @param apiResponseEnumInterface 接口
     * @param <T>                      返回的数据类型
     * @return 返回结果
     */
    public static <T> ApiResponseVO<T> success(ApiResponseEnumInterface apiResponseEnumInterface) {
        return success(apiResponseEnumInterface, null);
    }

    /**
     * 服务器异常
     *
     * @param <Void> 异常不需要返回数据
     * @return 封装的实体
     */
    public static <Void> ApiResponseVO<Void> error() {
        ApiResponseVO<Void> apiResponseVO = new ApiResponseVO<>();
        apiResponseVO.setData(null);
        apiResponseVO.setCode(ApiResponseEnum.error.getCode());
        apiResponseVO.setMsg(ApiResponseEnum.error.getMsg());
        return apiResponseVO;
    }

    /**
     * 自定义返回错误结果
     *
     * @param <Void> 异常不需要返回数据
     * @return 封装的实体
     */
    public static <Void> ApiResponseVO<Void> error(ApiResponseEnumInterface apiResponseEnumInterface) {
        ApiResponseVO<Void> apiResponseVO = new ApiResponseVO<>();
        apiResponseVO.setData(null);
        apiResponseVO.setCode(apiResponseEnumInterface.getCode());
        apiResponseVO.setMsg(apiResponseEnumInterface.getMsg());
        return apiResponseVO;
    }

    /**
     * 返回错误信息, qpi 调用时,返回错误信息,去除数据,转换格式
     *
     * @param apiResponse 响应实体封装
     * @param <Void>      Void
     * @return 响应实体封装
     */
    @SuppressWarnings("unchecked")
    public static <Void> ApiResponseVO<Void> error(ApiResponseVO apiResponse) {
        ApiResponseVO<Void> apiResponseVO = new ApiResponseVO<>();
        apiResponseVO.setData(null);
        apiResponseVO.setCode(apiResponse.getCode());
        apiResponseVO.setMsg(apiResponse.getMsg());
        return apiResponseVO;
    }
}

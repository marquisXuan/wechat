package com.cjwy.wxframework.authorization.controller.api;

import com.cjwy.projects.commons.domain.vo.ApiResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 9:50 下午
 */
@Api(value = "获取微信公众号授权 URL 控制器", tags = "获取微信公众号授权")
@RequestMapping(value = "api/v1", headers = {"version=v1"})
public interface WxGetAuthUrlControllerApi {

    /**
     * 生成默认 state 的授权 URL
     *
     * @return URL
     */
    @ApiOperation(value = "生成默认 state 的授权 URL", notes = "生成随机 32 位 UUID 字符串为 state 的方法")
    @GetMapping("auth")
    ApiResponseVO<String> requestAuthUrl();

    /**
     * 生成自定义 state 的授权 URL
     *
     * @param state 自定义 state
     * @return URL
     */
    @GetMapping("auth/{state}")
    @ApiOperation(value = "生成自定义 state 的授权 URL", notes = "生成可以表达业务涵意的字符串为 state 的方法")
    @ApiImplicitParam(value = "业务标识", name = "state", paramType = "path")
    ApiResponseVO<String> requestAuthUrl(@PathVariable String state);
}

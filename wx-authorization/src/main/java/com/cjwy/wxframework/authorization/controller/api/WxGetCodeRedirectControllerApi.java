package com.cjwy.wxframework.authorization.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.cjwy.wxframework.authorization.domain.constant.APIConstant.REDIRECT_CONTROLLER_MAPPING;
import static com.cjwy.wxframework.authorization.domain.constant.APIConstant.REDIRECT_METHOD_MAPPING;

/**
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/28 12:15 上午
 */
@RequestMapping(REDIRECT_CONTROLLER_MAPPING)
public interface WxGetCodeRedirectControllerApi {
    /**
     * 用户授权后回调地址
     *
     * @param code     code
     * @param state    业务参数
     * @param response 用于重定向到新页面
     */
    @GetMapping(REDIRECT_METHOD_MAPPING)
    void getCodeThenRedirectMethod(String code, String state, HttpServletResponse response) throws IOException;
}

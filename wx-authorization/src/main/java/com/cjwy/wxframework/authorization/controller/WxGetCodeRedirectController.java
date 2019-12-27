package com.cjwy.wxframework.authorization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cjwy.wxframework.authorization.domain.constant.APIConstant.REDIRECT_CONTROLLER_MAPPING;
import static com.cjwy.wxframework.authorization.domain.constant.APIConstant.REDIRECT_METHOD_MAPPING;

/**
 * 微信授权 Code 后请求的控制器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 12:43 下午
 */
@RestController
@RequestMapping(REDIRECT_CONTROLLER_MAPPING)
public class WxGetCodeRedirectController {

    /**
     * 用户授权使用 Code 后,重定向的方法
     */
    @GetMapping(REDIRECT_METHOD_MAPPING)
    public void getCodeThenRedirectMethod(String code, String state) {

    }
}

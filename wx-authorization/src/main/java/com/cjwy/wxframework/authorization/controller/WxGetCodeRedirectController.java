package com.cjwy.wxframework.authorization.controller;

import com.cjwy.wxframework.authorization.controller.api.WxGetCodeRedirectControllerApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信授权 Code 后请求的控制器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 12:43 下午
 */
@RestController
public class WxGetCodeRedirectController implements WxGetCodeRedirectControllerApi {

    /**
     * 用户授权使用 Code 后,重定向的方法
     */
    @Override
    public void getCodeThenRedirectMethod(String code, String state) {

    }
}

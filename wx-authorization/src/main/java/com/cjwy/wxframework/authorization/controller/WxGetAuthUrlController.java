package com.cjwy.wxframework.authorization.controller;

import com.cjwy.projects.commons.http.domain.vo.ApiResponseVO;
import com.cjwy.projects.commons.http.utils.UtilApiResponse;
import com.cjwy.wxframework.authorization.controller.api.WxGetAuthUrlControllerApi;
import com.cjwy.wxframework.authorization.service.WxGetAuthUrlService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 微信认证授权的 URL 控制器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 8:39 下午
 */
@RestController
public class WxGetAuthUrlController implements WxGetAuthUrlControllerApi {

    @Resource
    private WxGetAuthUrlService authUrlService;

    @Override
    public ApiResponseVO<String> requestAuthUrl() {
        String wxAuthUrl = authUrlService.getWxAuthUrl();
        return UtilApiResponse.success(wxAuthUrl);
    }

    @Override
    public ApiResponseVO<String> requestAuthUrl(@PathVariable String state) {
        String wxAuthUrl = authUrlService.getWxAuthUrl(state);
        return UtilApiResponse.success(wxAuthUrl);
    }
}
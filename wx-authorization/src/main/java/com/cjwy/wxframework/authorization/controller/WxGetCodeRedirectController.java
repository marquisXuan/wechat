package com.cjwy.wxframework.authorization.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.cjwy.projects.commons.cache.service.CacheService;
import com.cjwy.projects.commons.wx.pubnum.domain.vo.auth.response.ResponseByCode2AuthAccessTokenVO;
import com.cjwy.wxframework.authorization.controller.api.WxGetCodeRedirectControllerApi;
import com.cjwy.wxframework.authorization.domain.constant.WxCacheConstant;
import com.cjwy.wxframework.authorization.domain.properties.AuthWxProperties;
import com.cjwy.wxframework.authorization.domain.properties.ProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信授权 Code 后请求的控制器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 12:43 下午
 */
@Slf4j
@RestController
public class WxGetCodeRedirectController implements WxGetCodeRedirectControllerApi {

    /**
     * 缓存接口
     */
    @Resource
    private CacheService<String, String> cacheService;
    /**
     * 微信授权配置
     */
    @Resource
    private AuthWxProperties authWxProperties;
    /**
     * 项目配置
     */
    @Resource
    private ProjectProperties projectProperties;

    /**
     * 用户授权使用 Code 后,重定向的方法
     */
    @Override
    public void getCodeThenRedirectMethod(String code, String state, HttpServletResponse response) {
        // 使用code 换取 AuthAccessToken
        String code2AuthAccessTokenUrl = authWxProperties.getCode2AuthAccessToken();
        String wxCode2AuthAccessTokenResult;
        try {
            // 发送请求
            wxCode2AuthAccessTokenResult = HttpUtil.get(code2AuthAccessTokenUrl + code);
        } catch (Exception e) {
            log.error("[getCodeThenRedirectMethod] -> [使用 code 获取用户 AccessToken失败]", e);
            return;
        }
        ResponseByCode2AuthAccessTokenVO responseByCode2AuthAccessTokenVO = JSONObject.parseObject(wxCode2AuthAccessTokenResult, ResponseByCode2AuthAccessTokenVO.class);
        String errcode = responseByCode2AuthAccessTokenVO.getErrcode();
        if (null != errcode) {
            // 说明返回结果有问题
            try {
                response.sendRedirect(projectProperties.getRedirectPageUrl() + "?code=" + errcode + "&msg=" + responseByCode2AuthAccessTokenVO.getErrmsg());
            } catch (IOException e) {
                return;
            }
        }
        responseByCode2AuthAccessTokenVO.saveState(state);
        responseByCode2AuthAccessTokenVO.recordCreateTime();
        String userOpenId = responseByCode2AuthAccessTokenVO.getOpenid();
        // 缓存用户的 AuthAccessToken 30 天
        cacheService.cacheValue(WxCacheConstant.WX_USER_AUTH_ACCESS_TOKEN_KEY_PREFIX + userOpenId, JSONObject.toJSONString(responseByCode2AuthAccessTokenVO), 30 * 24 * 3600);
        if (projectProperties.isSilence()) {
            // todo 2019-12-28 静默授权的通用业务逻辑
        }
        // 非静默授权的业务逻辑 - 使用accessToken 请求用户信息
        String responseUserInfoString = HttpUtil.get(authWxProperties.getRequestUserInfoByAuthAccessToken()
                + responseByCode2AuthAccessTokenVO.getAccessToken() + "&openid="
                + responseByCode2AuthAccessTokenVO.getOpenid());
    }
}
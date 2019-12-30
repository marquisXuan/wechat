package com.cjwy.wxframework.authorization.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.cjwy.projects.commons.cache.service.CacheService;
import com.cjwy.projects.commons.http.domain.enumm.ApiResponseEnum;
import com.cjwy.projects.commons.wx.pubnum.domain.entity.ResponseWxUserInfoEntity;
import com.cjwy.projects.commons.wx.pubnum.domain.vo.auth.response.AuthAccessTokenVO;
import com.cjwy.wxframework.authorization.controller.api.WxGetCodeRedirectControllerApi;
import com.cjwy.wxframework.authorization.domain.constant.WxCacheConstant;
import com.cjwy.wxframework.authorization.domain.properties.AuthWxProperties;
import com.cjwy.wxframework.authorization.domain.properties.ProjectProperties;
import com.cjwy.wxframework.authorization.rpc.service.WxPublicNumberUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
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
     * 微信公众号用户业务
     */
    @Resource
    private WxPublicNumberUserInfoService wxPublicNumberUserInfoService;

    /**
     * 用户授权使用 Code 后,重定向的方法
     */
    @Override
    public void getCodeThenRedirectMethod(String code, String state, HttpServletResponse response) throws IOException {
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
        AuthAccessTokenVO authAccessTokenVO = JSONObject.parseObject(wxCode2AuthAccessTokenResult, AuthAccessTokenVO.class);
        long errcode = authAccessTokenVO.getErrcode();
        if (errcode != 0L) {
            // 说明返回结果有问题
            response.sendRedirect(projectProperties.getRedirectPageUrl() + "?code=" + errcode + "&msg=" + authAccessTokenVO.getErrmsg());
        }
        authAccessTokenVO.saveState(state);
        authAccessTokenVO.recordCreateTime();
        String userOpenId = authAccessTokenVO.getOpenid();
        // 缓存用户的 AuthAccessToken 30 天
        cacheService.cacheValue(WxCacheConstant.WX_USER_AUTH_ACCESS_TOKEN_KEY_PREFIX + userOpenId, JSONObject.toJSONString(authAccessTokenVO), 30 * 24 * 3600);
        if (projectProperties.isSilence()) {
            // todo 2019-12-28 静默授权的通用业务逻辑
        }
        // 非静默授权的业务逻辑 - 使用accessToken 请求用户信息
        String responseUserInfoString = HttpUtil.get(authWxProperties.getRequestUserInfoByAuthAccessToken()
                + authAccessTokenVO.getAccessToken() + "&openid="
                + authAccessTokenVO.getOpenid());
        ResponseWxUserInfoEntity responseWxUserInfoEntity = JSONObject.parseObject(responseUserInfoString, ResponseWxUserInfoEntity.class);
        if (!StringUtils.isEmpty(responseWxUserInfoEntity.getErrcode())) {
            response.sendRedirect(projectProperties.getRedirectPageUrl() + "?code=" + responseWxUserInfoEntity.getErrcode() + "&msg=" + responseWxUserInfoEntity.getErrmsg());
        }
        String businessToken = wxPublicNumberUserInfoService.generateTokenByWxPublicNumberUserInfo(responseWxUserInfoEntity);
        if (StringUtils.isEmpty(businessToken)) {
            response.sendRedirect(projectProperties.getRedirectPageUrl() + "?code=" + ApiResponseEnum.authentication_token_generate_error.getCode() + "&msg=" + ApiResponseEnum.authentication_token_generate_error.getMsg());
        }
        response.sendRedirect(projectProperties.getRedirectPageUrl() + "?code=" + ApiResponseEnum.success.getCode() + "&msg=" + ApiResponseEnum.success.getMsg() + "&data=" + businessToken);
    }
}
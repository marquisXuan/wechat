package com.cjwy.wxframework.authorization.service.achieve;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.cjwy.projects.commons.cache.service.CacheService;
import com.cjwy.projects.commons.wx.pubnum.domain.vo.auth.response.AuthAccessTokenVO;
import com.cjwy.wxframework.authorization.domain.constant.WxCacheConstant;
import com.cjwy.wxframework.authorization.domain.properties.AuthWxProperties;
import com.cjwy.wxframework.authorization.service.RefreshAuthAccessTokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/28 11:14 下午
 */
@Service
public class RefreshAuthAccessTokenServiceImpl implements RefreshAuthAccessTokenService {

    /**
     * 微信认证授权配置
     */
    @Resource
    private AuthWxProperties authWxProperties;

    @Resource
    private CacheService<String, String> cacheService;

    @Override
    public String refreshAuthAccessTokenByRefreshToken(AuthAccessTokenVO refreshToken) {
        String refreshAuthAccessTokenUrl = authWxProperties.getRefreshAuthAccessToken();
        String refreshTokenResultStr = HttpUtil.get(refreshAuthAccessTokenUrl + refreshToken.getRefreshToken());
        AuthAccessTokenVO authAccessTokenVO = JSONObject.parseObject(refreshTokenResultStr, AuthAccessTokenVO.class);
        authAccessTokenVO.saveState(refreshToken.getState());
        authAccessTokenVO.recordCreateTime();
        cacheService.cacheValue(WxCacheConstant.WX_USER_AUTH_ACCESS_TOKEN_KEY_PREFIX + authAccessTokenVO.getOpenid(), JSONObject.toJSONString(authAccessTokenVO), 30 * 24 * 3600);
        return authAccessTokenVO.getAccessToken();
    }
}

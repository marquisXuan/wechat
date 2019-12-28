package com.cjwy.wxframework.authorization.service;

import com.cjwy.projects.commons.wx.pubnum.domain.vo.auth.response.AuthAccessTokenVO;

/**
 * 刷新认证 Token 业务
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/28 11:10 下午
 */
public interface RefreshAuthAccessTokenService {
    /**
     * 通过access_token获取到的refresh_token参数重新刷新 Token
     *
     * @param refreshToken 通过access_token获取到的refresh_token参数
     * @return accessToken
     */
    String refreshAuthAccessTokenByRefreshToken(AuthAccessTokenVO refreshToken);
}

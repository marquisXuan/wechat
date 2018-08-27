package org.yyx.wx.acount.auth.service;


import org.yyx.wx.commons.vo.pubnum.reponse.BaseAccessToken;
import org.yyx.wx.commons.vo.pubnum.reponse.auth.AuthAccessToken;

/**
 * IAccessTokenService
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-10:57
 */
public interface IAccessTokenService {
    /**
     * 获取认证授权的AccessToken
     *
     * @param code  微信返回code
     * @param state 业务参数
     * @return 微信返回的AccessToken
     */
    void getAuthAccessToken(String code, String state);

    /**
     * 获取认证授权Token的方法
     *
     * @param refreshToken 刷新Token
     * @return 认证授权Token
     */
    AuthAccessToken getAuthAccessToken(String refreshToken);

    /**
     * 获取AccessToken的方法
     * <p>内含使用线程局部变量判断token是否失效</p>
     *
     * @return 微信返回的AccessToken
     */
    BaseAccessToken getBaseAccessToken();
}

package com.cjwy.wxframework.authorization.service;

/**
 * 获取微信认证授权的业务接口
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 9:40 下午
 */
public interface WxGetAuthUrlService {

    /**
     * 设置自定义state 的授权 URL 链接
     *
     * @param state state
     * @return url
     */
    String getWxAuthUrl(String state);

    /**
     * 设置随机义state 的授权 URL 链接
     *
     * @return url
     */
    String getWxAuthUrl();
}

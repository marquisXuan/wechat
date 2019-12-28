package com.cjwy.wxframework.authorization.rpc.service;

import com.cjwy.projects.commons.wx.pubnum.domain.entity.ResponseWxUserInfoEntity;

/**
 * 微信公众号用户业务
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/28 1:50 下午
 */
public interface WxPublicNumberUserInfoService {

    /**
     * 通过微信公众号用户信息生成业务系统登陆 Token
     *
     * @param responseWxUserInfoEntity 微信公众号返回的用户信息
     * @return token
     */
    String generateTokenByWxPublicNumberUserInfo(ResponseWxUserInfoEntity responseWxUserInfoEntity);
}

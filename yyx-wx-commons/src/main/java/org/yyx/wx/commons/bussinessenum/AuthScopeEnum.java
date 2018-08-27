package org.yyx.wx.commons.bussinessenum;

/**
 * 授权枚举类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/26-02:24
 */
public enum AuthScopeEnum {

    /**
     * 应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid）
     */
    snsapi_base,
    /**
     * snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
     */
    snsapi_userinfo
}

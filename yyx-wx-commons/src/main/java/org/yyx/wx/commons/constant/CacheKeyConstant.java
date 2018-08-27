package org.yyx.wx.commons.constant;

/**
 * 缓存Key常量
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-15:01
 */
public class CacheKeyConstant {

    /**
     * 用于保存AccessToken的Key，该AccessToken不是用于获取用户OPENID的AccessToken
     */
    public static final String ACCESS_TOKEN_NO_OPENID = "accessTokenNoOpenID";
    /**
     * 用于认证的AccessToken
     */
    public static final String AUTH_ACCESS_TOKEN = "authAccessToken";

    /**
     * 用于刷新认证的AccessToken的AccessToken
     */
    public static final String REFRESH_AUTH_ACCESS_TOKEN = "refreshAuthAccessToken";
}

package org.yyx.wx.commons.vo.pubnum.reponse.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yyx.wx.commons.vo.pubnum.reponse.BaseResponse;

import java.io.Serializable;

/**
 * 认证的AccessToken
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/26-10:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthAccessToken extends BaseResponse implements Serializable {

    private static final long serialVersionUID = 704928868877582577L;
    /**
     * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     */
    private String access_token;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private long expires_in;

    /**
     * 用户刷新access_token
     * refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。
     */
    private String refresh_token;
    /**
     * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     */
    private String openid;
    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;

    public long getExpires_in() {
        return expires_in - 2000;
    }

}

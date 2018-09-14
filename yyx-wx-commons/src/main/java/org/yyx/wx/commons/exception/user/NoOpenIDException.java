package org.yyx.wx.commons.exception.user;

import org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx;

/**
 * 没有OPENID异常
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/14-15:51
 */
public class NoOpenIDException extends WxUserException {

    private static final long serialVersionUID = 9052524177279709288L;

    public NoOpenIDException() {
        super();
    }

    public NoOpenIDException(String message) {
        super(message);
    }

    public NoOpenIDException(ResponseCodeFromWx responseCodeFromWx) {
        super("[异常信息] " + responseCodeFromWx.getDescription() + " : [code] " + responseCodeFromWx.getCode());
    }
}

package org.yyx.wx.commons.exception.user;

import org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx;

/**
 * 微信用户异常
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/14-15:51
 */
public class WxUserException extends RuntimeException {
    private static final long serialVersionUID = 311125352997652292L;

    public WxUserException() {
        super();
    }

    public WxUserException(String message) {
        super(message);
    }

    public WxUserException(ResponseCodeFromWx responseCodeFromWx) {
        super("[异常信息] " + responseCodeFromWx.getDescription() + " : [code] " + responseCodeFromWx.getCode());
    }
}

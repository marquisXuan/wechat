package org.yyx.wx.commons.exception.token;

import org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx;

/**
 * token异常
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/14-16:30
 */
public class AccessTokenException extends RuntimeException {

    private static final long serialVersionUID = -2311418251649507508L;

    public AccessTokenException() {
        super();
    }

    public AccessTokenException(String message) {
        super(message);
    }

    public AccessTokenException(ResponseCodeFromWx responseCodeFromWx) {
        super("[异常信息] " + responseCodeFromWx.getDescription() + " : [code] " + responseCodeFromWx.getCode());
    }
}

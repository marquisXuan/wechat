package org.yyx.wx.commons.exception.config;

import org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx;

/**
 * 配置异常
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/15-01:24
 */
public class ConfigException extends RuntimeException {
    private static final long serialVersionUID = -2092189170490889993L;


    public ConfigException() {
        super();
    }

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(ResponseCodeFromWx responseCodeFromWx) {
        super("[异常信息] " + responseCodeFromWx.getDescription() + " : [code] " + responseCodeFromWx.getCode());
    }
}

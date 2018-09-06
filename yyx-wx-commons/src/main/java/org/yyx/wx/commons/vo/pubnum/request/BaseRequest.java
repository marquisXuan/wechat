package org.yyx.wx.commons.vo.pubnum.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信基础响应
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/6-12:03
 */
@Data
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = 1998773449623319969L;
    /**
     * 响应信息
     */
    private String errmsg;
    /**
     * 响应码
     */
    private Integer errcode;
}

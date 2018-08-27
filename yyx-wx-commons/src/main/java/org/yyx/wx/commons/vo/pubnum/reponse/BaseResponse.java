package org.yyx.wx.commons.vo.pubnum.reponse;

import lombok.Data;

/**
 * 返回数据结构
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-23:41
 */
@Data
public abstract class BaseResponse {
    /**
     * 返回码
     */
    private long errcode = 0L;

    /**
     * 说明
     */
    private String errmsg = "request success";
}

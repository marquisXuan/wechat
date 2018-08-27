package org.yyx.wx.commons.vo.pubnum.reponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-10:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseAccessToken extends BaseResponse implements Serializable {

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = -3098025874564653952L;
    /**
     * 获取到的凭证
     */
    private String access_token;

    /**
     * 凭证有效时间，单位：秒
     */
    private long expires_in;

    public long getExpires_in() {
        return expires_in - 2000;
    }
}

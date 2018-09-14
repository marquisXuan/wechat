package org.yyx.wx.commons.vo.pubnum.response.user;

import lombok.Data;
import lombok.NonNull;

import static org.yyx.wx.commons.constant.ResponseConstant.LANG_DEFAULT;

/**
 * 批量获取用户信息的实体类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/14-17:06
 */
@Data
public class BatchUserResponse {
    /**
     * 用户的标识，对当前公众号唯一
     */
    @NonNull
    private String openid;
    /**
     * 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为zh-CN
     */
    private String lang = LANG_DEFAULT;
}

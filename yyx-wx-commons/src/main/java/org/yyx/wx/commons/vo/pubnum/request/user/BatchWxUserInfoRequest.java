package org.yyx.wx.commons.vo.pubnum.request.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yyx.wx.commons.vo.pubnum.request.BaseRequest;

import java.util.List;

/**
 * 批量返回的用户信息
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/14-17:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BatchWxUserInfoRequest extends BaseRequest {
    private static final long serialVersionUID = 2570067084489413002L;

    /**
     * 用户集合
     */
    private List<WxUserInfoRequest> user_info_list;
}

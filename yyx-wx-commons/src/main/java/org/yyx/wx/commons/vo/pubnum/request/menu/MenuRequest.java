package org.yyx.wx.commons.vo.pubnum.request.menu;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.yyx.wx.commons.vo.pubnum.request.BaseRequest;

import java.util.List;

/**
 * 微信推送的菜单实体
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/10/14-16:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuRequest extends BaseRequest {
    /**
     * 序列化唯一标识
     */
    private static final long serialVersionUID = -4270617815461552424L;
    /**
     * 默认菜单
     */
    private MenuBeanRequest menu;
    /**
     * 个性化菜单
     */
    private List<ConditionalMenuBeanRequest> conditionalmenu;
}

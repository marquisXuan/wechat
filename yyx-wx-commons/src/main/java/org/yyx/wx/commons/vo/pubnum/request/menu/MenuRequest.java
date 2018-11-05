package org.yyx.wx.commons.vo.pubnum.request.menu;

import java.util.List;

/**
 * 微信推送的菜单实体
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/10/14-16:34
 */
public class MenuRequest {

    /**
     * 默认菜单
     */
    private MenuBean menu;
    /**
     * 个性化菜单
     */
    private List<ConditionalMenuBean> conditionalmenu;
}

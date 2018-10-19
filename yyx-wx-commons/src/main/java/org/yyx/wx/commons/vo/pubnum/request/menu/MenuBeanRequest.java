package org.yyx.wx.commons.vo.pubnum.request.menu;

import lombok.Data;
import org.yyx.wx.commons.vo.pubnum.base.menu.ButtonBean;

/**
 * 默认菜单实体
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/10/14-16:46
 */
@Data
public class MenuBeanRequest {

    /**
     * 菜单ID。
     */
    private long menuid;
    /**
     * 按钮数组
     */
    private ButtonBean[] button;
}

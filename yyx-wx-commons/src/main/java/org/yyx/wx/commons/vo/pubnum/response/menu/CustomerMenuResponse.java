package org.yyx.wx.commons.vo.pubnum.response.menu;

import lombok.Data;
import org.yyx.wx.commons.vo.pubnum.base.menu.ButtonBean;

/**
 * 自定义菜单响应
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/19-15:17
 */
@Data
public class CustomerMenuResponse {

    /**
     * 一级菜单数组，个数应为1~3个
     */
    private ButtonBean[] button;

}
package org.yyx.wx.manage.menu.service;

import org.yyx.wx.commons.vo.pubnum.request.menu.MenuRequest;

/**
 * 菜单相关的业务接口
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/10/14-20:14
 */
public interface IMenuService {

    /**
     * 自定义菜单查询接口
     *
     * @return 自定义菜单
     */
    MenuRequest queryMenuList();
}

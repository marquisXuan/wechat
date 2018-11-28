package org.yyx.wx.manage.menu.service;

import org.yyx.wx.commons.vo.pubnum.request.BaseRequest;
import org.yyx.wx.commons.vo.pubnum.request.menu.MenuRequest;
import org.yyx.wx.commons.vo.pubnum.response.menu.CustomerMenuResponse;

/**
 * 菜单相关的业务接口
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/10/14-20:14
 */
public interface IMenuService {

    /**
     * 自定义菜单创建接口
     *
     * @param customerMenuResponse 要创建的菜单
     * @return 结果
     */
    BaseRequest createMenu(CustomerMenuResponse customerMenuResponse);

    /**
     * 自定义菜单查询接口
     *
     * @return 自定义菜单
     */
    MenuRequest queryMenuList();
}

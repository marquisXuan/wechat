package org.yyx.wx.manage.menu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyx.wx.commons.vo.pubnum.request.BaseRequest;
import org.yyx.wx.commons.vo.pubnum.request.menu.MenuRequest;
import org.yyx.wx.commons.vo.pubnum.response.menu.CustomerMenuResponse;
import org.yyx.wx.manage.menu.service.IMenuService;
import org.yyx.xf.tool.web.domain.entity.BaseResponse;
import org.yyx.xf.tool.web.util.UtilResponse;

import javax.annotation.Resource;

/**
 * 菜单相关的请求接口
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/10/14-20:06
 */
@RestController
@Api("菜单")
@RequestMapping("api/menu")
public class MenuController {

    /**
     * 菜单业务接口
     */
    @Resource
    private IMenuService menuService;

    /**
     * 自定义菜单创建接口
     *
     * @param customerMenuResponse 自定义菜单
     * @return 创建结果
     */
    @PostMapping("add")
    @ApiOperation(value = "创建一个新的自定义菜单", httpMethod = "POST", response = BaseResponse.class)
    @ApiImplicitParam(dataTypeClass = MenuRequest.class, value = "自定义菜单")
    public BaseResponse createMenu(@RequestBody CustomerMenuResponse customerMenuResponse) {
        BaseRequest menuStatus = menuService.createMenu(customerMenuResponse);
        return UtilResponse.success(menuStatus);
    }

    /**
     * 获取自定义菜单
     *
     * @return 查询到的菜单
     */
    @GetMapping("list")
    @ApiOperation(value = "获取自定义菜单接口", httpMethod = "GET", response = BaseResponse.class)
    public BaseResponse<MenuRequest> queryMenu() {
        MenuRequest menuRequest = menuService.queryMenuList();
        return UtilResponse.success(menuRequest);
    }
}

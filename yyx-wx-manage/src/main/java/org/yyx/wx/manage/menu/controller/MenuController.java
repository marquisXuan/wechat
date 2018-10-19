package org.yyx.wx.manage.menu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyx.domain.ResponseEntity;
import org.yyx.util.http.ResponseUtil;
import org.yyx.wx.commons.vo.pubnum.request.menu.MenuRequest;
import org.yyx.wx.manage.menu.service.IMenuService;

import javax.annotation.Resource;

import static org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx.getCode;
import static org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx.isSuccess;

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

    public ResponseEntity createMenu(MenuRequest menuRequest) {
        return ResponseUtil.success();
    }

    /**
     * 获取自定义菜单
     *
     * @return 查询到的菜单
     */
    @GetMapping("list")
    @ApiOperation(value = "获取自定义菜单接口", httpMethod = "GET", response = ResponseEntity.class)
    public ResponseEntity<MenuRequest> queryMenu() {
        MenuRequest menuRequest = menuService.queryMenuList();
        Integer errcode = menuRequest.getErrcode();
        boolean success = isSuccess(errcode);
        if (success) {
            return ResponseUtil.success(menuRequest);
        }
        return new ResponseEntity<>(Long.valueOf(errcode), getCode(errcode).getDescription(), menuRequest.getErrmsg());
    }
}

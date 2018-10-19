package org.yyx.wx.manage.menu.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.yyx.wx.acount.auth.service.IAccessTokenService;
import org.yyx.wx.commons.vo.pubnum.request.auth.BaseAccessTokenRequest;
import org.yyx.wx.commons.vo.pubnum.request.menu.MenuRequest;
import org.yyx.wx.manage.menu.config.WxPubNumMenuConfig;
import org.yyx.wx.manage.menu.service.IMenuService;

import javax.annotation.Resource;

/**
 * 菜单业务接口实现
 * <p>
 *w
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/10/14-22:40
 */
@Service
public class MenuServiceImpl implements IMenuService {

    /**
     * 微信公众号菜单配置类
     */
    @Resource
    private WxPubNumMenuConfig wxPubNumMenuConfig;

    @Resource
    private IAccessTokenService accessTokenService;

    /**
     * 查询自定义菜单方法
     *
     * @return 自定义菜单
     */
    @Override
    public MenuRequest queryMenuList() {
        BaseAccessTokenRequest baseAccessToken = accessTokenService.getBaseAccessToken();
        String menuListJson = HttpUtil.get(wxPubNumMenuConfig.getSearchMenuList() + baseAccessToken.getAccess_token());
        return JSONObject.parseObject(menuListJson, MenuRequest.class);
    }
}

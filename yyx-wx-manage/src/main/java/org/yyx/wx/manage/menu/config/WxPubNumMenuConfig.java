package org.yyx.wx.manage.menu.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信公众号菜单配置
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/10/14-22:42
 */
@Component
@Data
public class WxPubNumMenuConfig {

    /**
     * 查询自定义菜单的URL
     */
    @Value("${wx.url.menu.search_menu}")
    private String searchMenuList;
    /**
     * 创建自定义菜单URL
     */
    @Value("${wx.url.menu.create_menu}")
    private String createCustomMenu;
}

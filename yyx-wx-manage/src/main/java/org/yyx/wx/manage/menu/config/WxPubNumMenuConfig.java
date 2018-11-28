package org.yyx.wx.manage.menu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "wx.url.menu")
public class WxPubNumMenuConfig {

    /**
     * 查询自定义菜单的URL
     */
    private String searchMenuList;
    /**
     * 创建自定义菜单URL
     */
    private String createCustomMenu;
}

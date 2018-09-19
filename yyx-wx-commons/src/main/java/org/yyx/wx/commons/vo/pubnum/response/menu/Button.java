package org.yyx.wx.commons.vo.pubnum.response.menu;

import lombok.Data;

/**
 * 按钮
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/19-15:18
 */
@Data
public class Button {

    /*
     * 1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
     * 2、一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。
     * 3、创建自定义菜单后，菜单的刷新策略是，在用户进入公众号会话页或公众号profile页时，如果发现上一次拉取菜单的请求在5分钟以前，就会拉取一下菜单，
     * 如果菜单有更新，就会刷新客户端的菜单。测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
     */

    /**
     * 菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    private String name;
    /**
     * 二级菜单数组，个数应为1~5个
     */
    private Button[] sub_button = new Button[5];
    /**
     * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    private String type;
    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节  当type为click时此字段不能为空
     */
    private String key;
    /**
     * 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
     */
    private String url;
    /**
     * 调用新增永久素材接口返回的合法media_id
     */
    private String media_id;
    /**
     * 小程序的appid（仅认证公众号可配置）
     */
    private String appid;
    /**
     * 小程序的页面路径
     */
    private String pagepath;
}

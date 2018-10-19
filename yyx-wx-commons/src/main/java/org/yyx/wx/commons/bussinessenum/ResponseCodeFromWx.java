package org.yyx.wx.commons.bussinessenum;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信响应码
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/14-15:55
 */
public enum ResponseCodeFromWx {
    // region 微信响应码

    /**
     * 系统繁忙
     */
    busy_server(-1, "系统繁忙，此时请开发者稍候再试"),
    /**
     * 请求成功
     */
    success(0, "请求成功"),
    /**
     * 获取 access_token 时 AppSecret 错误，或者 access_token 无效。
     * 请开发者认真比对 AppSecret 的正确性，或查看是否正在为恰当的公众号调用接口
     */
    error_app_secret_or_access_token(40001, "获取 access_token 时 AppSecret 错误，或者 access_token 无效。"),
    /**
     * 不合法的凭证类型
     */
    illegal_certificate_type(40002, "不合法的凭证类型"),
    /**
     * 不合法的 OpenID ，请开发者确认 OpenID （该用户）是否已关注公众号，或是否是其他公众号的 OpenID
     */
    illegal_open_id(40003, "不合法的 OpenID"),
    /**
     * 不合法的媒体文件类型
     */
    illegal_media_type(40004, "不合法的媒体文件类型"),
    /**
     * 不合法的文件类型
     */
    illegal_file_type(40005, "不合法的文件类型"),
    /**
     * 不合法的文件大小
     */
    illegal_file_size(40006, "不合法的文件大小"),
    /**
     * 不合法的媒体文件 id
     */
    illegal_media_id(40007, "不合法的媒体文件 id"),
    /**
     * 不存在的菜单数据
     */
    no_menu(46003, "不存在的菜单数据"),
    // endregion
    // region 业务响应码

    /**
     * 配置加载异常
     */
    error_load_config(99997, "配置加载异常"),
    /**
     * OPENID不能为空
     */
    no_open_id(99998, "OPENID不能为空"),
    /**
     * 没有找到对应的响应码
     */
    error(99999, "没有找到对应的响应码");
    // endregion

    /**
     * 说明
     */
    @Getter
    @Setter
    private String description;
    /**
     * 微信的返回码
     */
    @Getter
    @Setter
    private int code;

    ResponseCodeFromWx(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 获取响应码对象
     *
     * @param code 响应码
     * @return 响应码对象
     */
    public static ResponseCodeFromWx getCode(int code) {
        ResponseCodeFromWx[] values = ResponseCodeFromWx.values();
        for (int i = 0; i < values.length; i++) {
            ResponseCodeFromWx value = values[i];
            if (code == value.code) {
                return value;
            }
        }
        return error;
    }

    /**
     * 判断返回是否成功
     *
     * @param code 返回的code码
     * @return true:成功 false:失败
     */
    public static boolean isSuccess(int code) {
        return code == success.code;
    }
}

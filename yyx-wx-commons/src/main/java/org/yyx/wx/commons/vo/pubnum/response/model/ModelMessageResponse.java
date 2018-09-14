package org.yyx.wx.commons.vo.pubnum.response.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Map;

/**
 * 模板消息VO
 * <p>
 * <p>
 * url和miniprogram都是非必填字段，若都不传则模板无跳转；
 * 若都传，会优先跳转至小程序。开发者可根据实际需要选择其中一种跳转方式即可。
 * 当用户的微信客户端版本不支持跳小程序时，将会跳转至url。
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/1-09:51
 */
@Data
@NoArgsConstructor
public class ModelMessageResponse {
    /**
     * 接收者openid
     */
    @NonNull
    private String touser;
    /**
     * 模板ID
     */
    @NonNull
    private String template_id;

    /**
     * 模板跳转链接
     */
    private String url;

    /**
     * 跳小程序所需数据，不需跳小程序可不用传该数据
     */
    private MiniProgramResponse miniprogram;
    /**
     * 模板数据
     */
    @NonNull
    private Map<String, ModelDataResponse> data;

    /**
     * 跳小程序所需数据，不需跳小程序可不用传该数据
     */
    @Data
    @NoArgsConstructor
    public class MiniProgramResponse {
        /**
         * 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏)
         */
        @NonNull
        private String appid;

        /**
         * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏
         */
        private String pagepath;
    }

    /**
     * 模板数据
     */
    @NoArgsConstructor
    @Data
    public class ModelDataResponse {
        @NonNull
        private String value;
        /**
         * 模板内容字体颜色，不填默认为黑色
         */
        private String color;
    }
}

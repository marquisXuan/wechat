package org.yyx.wx.acount.user.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信用户配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/14-15:21
 */
@Component
@Data
public class WxUserInfoConfig {
    /**
     * 获取用户基本信息的URL
     */
    @Value("${wx.url.base_user_info}")
    private String urlBaseUserInfo;
    /**
     * 批量获取用户基本信息的URL
     */
    @Value("${wx.url.batch_user_info}")
    private String urlBatchUserInfo;
}

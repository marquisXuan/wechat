package com.cjwy.wxframework.authorization.domain.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 项目配置
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 12:32 下午
 */
@Data
@ConfigurationProperties("project")
public class ProjectProperties {

    /**
     * 项目的域名
     */
    private String domain;

    /**
     * 静默授权
     * true : 不弹出授权框  只能拿到 openId
     * false : 弹出授权框
     * 默认为 false
     */
    private boolean silence = false;
    /**
     * 重定向到页面的请求 URL
     * http://xxxxx.domain.com/projectName/pageUrl?code=xxx&msg=xxx&data=xxx
     */
    private String redirectPageUrl;
}

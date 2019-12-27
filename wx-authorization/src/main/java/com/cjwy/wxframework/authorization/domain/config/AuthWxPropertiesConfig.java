package com.cjwy.wxframework.authorization.domain.config;

import com.cjwy.wxframework.authorization.domain.properties.AuthWxProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 启动微信授权的配置
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 12:34 下午
 */
@Component
@EnableConfigurationProperties(AuthWxProperties.class)
public class AuthWxPropertiesConfig {

}

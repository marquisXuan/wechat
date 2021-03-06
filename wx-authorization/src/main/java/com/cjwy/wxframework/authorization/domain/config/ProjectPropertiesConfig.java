package com.cjwy.wxframework.authorization.domain.config;

import com.cjwy.wxframework.authorization.domain.properties.ProjectProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 项目配置启动
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 12:35 下午
 */
@Configuration
@EnableConfigurationProperties(ProjectProperties.class)
public class ProjectPropertiesConfig {

}

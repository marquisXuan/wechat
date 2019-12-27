package com.cjwy.wxframework.authorization.domain.config;

import com.cjwy.wxframework.authorization.domain.properties.ProjectProperties;
import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 项目配置启动
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 12:35 下午
 */
@Data
@EnableConfigurationProperties(ProjectProperties.class)
public class ProjectPropertiesConfig {

}

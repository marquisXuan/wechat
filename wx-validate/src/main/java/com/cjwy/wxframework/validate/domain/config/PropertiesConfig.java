package com.cjwy.wxframework.validate.domain.config;

import com.cjwy.wxframework.validate.domain.properties.BasePublicNumberProperties;
import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 用于配置系统参数配置项的配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/20 10:57 上午
 */
@Data
@Configuration
@EnableConfigurationProperties(BasePublicNumberProperties.class)
public class PropertiesConfig {

}

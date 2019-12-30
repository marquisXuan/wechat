package com.cjwy.projects.commons.swagger.condition;

import com.cjwy.projects.commons.swagger.config.Swagger2Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 条件注入
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/30 8:08 下午
 */
@Configuration
@EnableSwagger2
public class SwaggerCondition {

    /**
     * Swagger 2 配置
     *
     * @return Swagger2 对象
     */
    @Bean
    @ConditionalOnMissingBean(name = "swagger2Config")
    public Swagger2Config swagger2Config() {
        return new Swagger2Config();
    }
}

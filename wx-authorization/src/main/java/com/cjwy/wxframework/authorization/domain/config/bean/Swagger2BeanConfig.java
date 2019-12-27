package com.cjwy.wxframework.authorization.domain.config.bean;

import com.cjwy.wxframework.authorization.domain.config.Swagger2Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger2Bean
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 10:09 下午
 */
@Configuration
public class Swagger2BeanConfig {


    @Bean("Swagger2Config")
    @ConditionalOnClass(name = "Swagger2Config")
    public Swagger2Config swagger2Config() {
        return new Swagger2Config();
    }
}

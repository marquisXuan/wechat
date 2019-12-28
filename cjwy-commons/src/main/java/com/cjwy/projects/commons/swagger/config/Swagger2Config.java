package com.cjwy.projects.commons.swagger.config;

import com.cjwy.projects.commons.swagger.domain.entity.SwaggerEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2Config
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 10:00 下午
 */
@Component
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createWxAuthorizationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("微信公众号授权模块")
                .apiInfo(SwaggerEntity.apiYunXuanYe("微信公众号授权 API", "主要用于微信公众号授权功能", "v1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cjwy.wxframework.authorization.controller"))
                .paths(PathSelectors.any())
                .build()
                ;
    }

    @Bean
    public Docket createWxValidateApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("微信公众号验证模块")
                .apiInfo(SwaggerEntity.apiYunXuanYe("微信公众号验证 API", "主要用于微信公众号验证功能", "v1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cjwy.wxframework.validate.controller"))
                .paths(PathSelectors.any())
                .build()
                ;
    }

}

package com.cjwy.wxframework.authorization.domain.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
@EnableSwagger2
public class Swagger2Config {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("微信公众号API")
                //创建人
                .contact(new Contact("叶云轩", "http://www.happyqing.com", "yyx_cjwy@163.com"))
                //版本号
                .version("1.0")
                //描述
                .description("楚基伟业微信公众号授权模块")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.cjwy.wxframework.authorization.controller.api"))
                .paths(PathSelectors.any())
                .build();
    }
}

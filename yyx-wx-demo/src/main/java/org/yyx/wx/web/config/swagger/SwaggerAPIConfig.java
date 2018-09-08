package org.yyx.wx.web.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 接口文档配置类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-12:53
 */
@Configuration
@EnableSwagger2
public class SwaggerAPIConfig {

    private static final String LICENSE = "叶云轩";
    private static final String LICENSE_URL = "https://www.baidu.com?s=叶云轩";
    private static final String TITLE = "微信接口文档说明";
    /**
     * 联系信息
     */
    private static final Contact YYX_CONTACT = new Contact("叶云轩", "https://www.baidu.com?s=叶云轩", "tdg_yyx@foxmail.com");

    /**
     * 员工组接口文档
     *
     * @return docket
     */
    @Bean
    public Docket createEmployeeAPI() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("微信")
                .apiInfo(wxAPIInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.yyx.wx.web.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo wxAPIInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description("微信相关功能性接口")
                .version("1.0")
                .contact(YYX_CONTACT)
                .termsOfServiceUrl("服务条款")
                .license(LICENSE)
                .licenseUrl(LICENSE_URL)
                .build();
    }
}

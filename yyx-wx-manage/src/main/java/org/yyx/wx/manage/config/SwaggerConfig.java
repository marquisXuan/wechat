package org.yyx.wx.manage.config;

import org.springframework.beans.factory.annotation.Value;
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
 * 接口文档配置
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/10/14-20:41
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String LICENSE = "叶云轩";
    private static final String LICENSE_URL = "https://www.baidu.com?s=叶云轩";
    private static final String TITLE = "微信便捷包接口文档说明";
    /**
     * 联系信息
     */
    private static final Contact YYX_CONTACT = new Contact("叶云轩", "https://www.baidu.com?s=叶云轩", "tdg_yyx@foxmail.com");


    @Value("${swagger.enable}")
    private boolean enableSwagger;

    /**
     * 菜单相关的接口文档
     *
     * @return docket
     */
    @Bean
    public Docket menuAPI() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("WeChat_Menu")
                .apiInfo(wxAPIInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.yyx.wx.manage.menu.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo wxAPIInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description("微信便捷包接口文档说明")
                .version("1.0")
                .contact(YYX_CONTACT)
                .termsOfServiceUrl("服务条款")
                .license(LICENSE)
                .licenseUrl(LICENSE_URL)
                .build();
    }
}

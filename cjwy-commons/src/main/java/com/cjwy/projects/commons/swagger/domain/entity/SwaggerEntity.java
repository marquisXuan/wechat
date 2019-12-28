package com.cjwy.projects.commons.swagger.domain.entity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * SwaggerEntity
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 10:37 下午
 */
public class SwaggerEntity {

    private static SwaggerAuthor authorYunXuanYe() {
        SwaggerAuthor swaggerAuthor = new SwaggerAuthor();
        swaggerAuthor.setEmail("marquis_xuan@163.com");
        swaggerAuthor.setName("叶云轩");
        swaggerAuthor.setWebUrl("http://www.happyqing.com");
        return swaggerAuthor;
    }

    public static ApiInfo apiYunXuanYe(String title, String description, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .contact(new Contact(authorYunXuanYe().getName(), authorYunXuanYe().getWebUrl(), authorYunXuanYe().getEmail()))
//                .termsOfServiceUrl("服务条款URL")
//                .license(LICENSE_DESCRIPTION)
//                .licenseUrl(LICENSE_DESCRIPTION_URL)
                .build();
    }
}

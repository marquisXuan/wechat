package org.yyx.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans({
        @ComponentScan("org.yyx.wx.acount.*"), @ComponentScan("org.yyx.wx.message.*"), @ComponentScan("org.yyx.wx.commons.*")
        , @ComponentScan("org.yyx.wx.web.*"), @ComponentScan("org.yyx.wx.user.*")
})
/**
 * 2
 *  @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/9/10 - 14:57
 */
public class WebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class);
    }
}

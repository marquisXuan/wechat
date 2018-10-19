package org.yyx.wx.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * 系统管理
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/10/14 - 23:00
 */
@SpringBootApplication
@ComponentScans({
        @ComponentScan("org.yyx.wx.acount.*"), @ComponentScan("org.yyx.wx.commons.*"),
        @ComponentScan("org.yyx.wx.manage.*")
})
public class ManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }
}

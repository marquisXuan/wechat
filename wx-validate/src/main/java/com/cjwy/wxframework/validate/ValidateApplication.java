package com.cjwy.wxframework.validate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


/**
 * 微信公众号对接时验证模块
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 */
@SpringBootApplication
public class ValidateApplication {

    /**
     * 启动方法
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(ValidateApplication.class, args);
    }
}

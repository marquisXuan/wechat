package com.cjwy.wxframework.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 微信公众号网页授权相关的模块
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2019/12/27 - 10:40 上午
 */
@SpringBootApplication
public class AuthorizationApplication {

    /**
     * main 方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }
}
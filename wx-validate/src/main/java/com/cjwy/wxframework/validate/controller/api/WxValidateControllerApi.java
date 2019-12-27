package com.cjwy.wxframework.validate.controller.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 10:51 下午
 */
@Api(value = "微信公众号服务器验证控制器", hidden = true)
public interface WxValidateControllerApi {


    /**
     * 验证消息来自微信服务器方法
     *
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     * @return 验证结果
     */
    @GetMapping("wx/validate")
    String accessGet(@RequestParam String signature,
                     @RequestParam String timestamp,
                     @RequestParam String nonce,
                     @RequestParam String echostr);
}

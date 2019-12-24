package com.cjwy.wxframework.validate.controller;

import com.cjwy.wxframework.validate.domain.exception.WxValidateException;
import com.cjwy.wxframework.validate.domain.factory.ExceptionFactory;
import com.cjwy.wxframework.validate.domain.properties.BasePublicNumberProperties;
import com.cjwy.wxframework.validate.utils.UtilValidateWeChat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * 微信服务器验证控制器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/25 7:00 上午
 */
@Slf4j
@RestController
public class WxValidateController {
    @Resource
    private BasePublicNumberProperties basePublicNumberProperties;

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
    public String accessGet(@RequestParam String signature,
                            @RequestParam String timestamp,
                            @RequestParam String nonce,
                            @RequestParam String echostr) {
        String result = "wrong";
        String token = basePublicNumberProperties.getToken();
        if (StringUtils.isEmpty(token)) {
            throw ExceptionFactory.createValidateException(WxValidateException.class, "请检查 Token 是否配置正确");
        }
        log.info("入参[signature]={},[timestamp]={},[nonce]={}，[echostr]={}", signature, timestamp, nonce, echostr);
        boolean validate = UtilValidateWeChat.validate(signature, timestamp, nonce, token);
        if (validate) {
            LOGGER.info("验证成功...");
            result = echostr;
        } else {
            log.info("验证失败...");
        }
        return result;
    }
}

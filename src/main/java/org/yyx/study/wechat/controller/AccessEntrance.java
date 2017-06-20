package org.yyx.study.wechat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yyx.study.wechat.util.ValidateWeChat;

import javax.annotation.Resource;

/**
 * 接入微信服务器入口
 * Created by 叶云轩 on 2017/6/20-10:59
 * Concat ycountjavaxuan@outlook.com
 */
@RestController
public class AccessEntrance {

    private Logger logger = LoggerFactory.getLogger(AccessEntrance.class);

    /**
     * 验证工具类
     */
    @Resource
    private ValidateWeChat validateWeChat;

    /**
     * 验证消息来自微信服务器方法
     *
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     * @return 验证结果
     */
    @GetMapping("/")
    public String accessGet(@RequestParam String signature, @RequestParam String timestamp,
                            @RequestParam String nonce, @RequestParam String echostr) {
        logger.info("method invoke...");
        logger.info("参数[signature]={},[timestamp]={},[nonce]={}，[echostr]={}", signature, timestamp, nonce, echostr);
        boolean validate = validateWeChat.validate(signature, timestamp, nonce);
        if (validate) {
            logger.info("验证成功...");
            return echostr;
        }
        logger.info("验证失败...");
        return "wrong";
    }
}

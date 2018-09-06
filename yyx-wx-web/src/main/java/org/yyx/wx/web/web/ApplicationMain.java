package org.yyx.wx.web.web;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.reponse.message.BaseMessageResponse;
import org.yyx.wx.commons.vo.pubnum.request.BaseMessageAndEventRequestAndResponse;
import org.yyx.wx.message.handler.AbstractMessageHandler;
import org.yyx.wx.message.handler.IMessageHandler;
import org.yyx.wx.web.util.ValidateWeChat;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.yyx.wx.message.factory.MessageEventHandlerFactory.getMessageHandler;

/**
 * 接入微信服务器入口
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 18:27
 */
@RestController
public class ApplicationMain {

    /**
     * AccessEntrance日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationMain.class);

    /**
     * 验证工具类
     */
    @Resource
    private ValidateWeChat validateWeChat;

    @Resource
    private IMessageHandler iMessageHandler;


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
    public String accessGet(@RequestParam String signature,
                            @RequestParam String timestamp,
                            @RequestParam String nonce,
                            @RequestParam String echostr) {
        LOGGER.info("参数[signature]={},[timestamp]={},[nonce]={}，[echostr]={}", signature, timestamp, nonce, echostr);
        boolean validate = validateWeChat.validate(signature, timestamp, nonce);
        if (validate) {
            LOGGER.info("验证成功...");
            return echostr;
        }
        LOGGER.info("验证失败...");
        return "wrong";
    }

    /**
     * 微信服务器推送过来的消息
     *
     * @param request 请求
     * @return 处理结果
     */
    @PostMapping("/")
    public String accessPost(HttpServletRequest request) {
        LOGGER.info("[进入处理微信请求的方法]");
        try {
            // 从微信请求中获取流
            ServletInputStream inputStream = request.getInputStream();
            // 创建一个xml解析对象
            SAXReader saxReader = new SAXReader();
            try {
                // 解析到一个Document中
                Document document = saxReader.read(inputStream);
                // 获取xml中根节点
                Element rootElement = document.getRootElement();
                // 解析成BaseMessage对象
                BaseMessageAndEventRequestAndResponse baseMessage = WxXmlAndObjectUtil.xmlToObject(rootElement, BaseMessageAndEventRequestAndResponse.class);
                AbstractMessageHandler messageHandler = getMessageHandler();
                messageHandler.setiMessageHandler(iMessageHandler);
                BaseMessageResponse baseMessageResponse = messageHandler.handleMessage(baseMessage, rootElement);
                LOGGER.info("[返回信息] {}", baseMessageResponse);
                return WxXmlAndObjectUtil.objectToxml(baseMessageResponse);
            } catch (DocumentException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}

package org.yyx.wx.web.web;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.util.XmlToObjectUtil;
import org.yyx.wx.commons.vo.pubnum.BaseMessage;
import org.yyx.wx.message.handler.event.ScanSubscribeEventHandler;
import org.yyx.wx.message.handler.event.SubscribeEventHandler;
import org.yyx.wx.message.handler.message.TextMessageHandler;
import org.yyx.wx.web.util.ValidateWeChat;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
                BaseMessage baseMessage = XmlToObjectUtil.xmlToObject(rootElement, BaseMessage.class);
                // 订阅事件处理器
                SubscribeEventHandler subscribeEventHandler = new SubscribeEventHandler();
                // todo 可以优化
                // 文本消息处理器
                TextMessageHandler textMessageHandler = new TextMessageHandler();
                // 扫码未关注公众号事件处理器
                ScanSubscribeEventHandler scanSubscribeEventHandler = new ScanSubscribeEventHandler();

                scanSubscribeEventHandler.setNextHandler(subscribeEventHandler);
                subscribeEventHandler.setNextHandler(textMessageHandler);
                BaseMessage baseMessageResponse = scanSubscribeEventHandler.handleMessage(baseMessage, rootElement);

                /*创建一个document*/
                Document documentResponse = DocumentHelper.createDocument();
                /*生成根节点*/
                Element rootElementResponse = documentResponse.addElement("xml");
                Element toUserName = rootElementResponse.addElement("ToUserName");
                toUserName.addCDATA(baseMessage.getFromUserName());
                Element fromUserName = rootElementResponse.addElement("FromUserName");
                fromUserName.addCDATA(baseMessage.getToUserName());
                Element cdata = rootElementResponse.addElement("CreateTime");
                cdata.addCDATA(System.currentTimeMillis() + "");
                Element MsgType = rootElementResponse.addElement("MsgType");
                MsgType.addCDATA(MessageTypeEnum.text.toString());
                Element content = rootElementResponse.addElement("Content");
                content.addCDATA("这是叶云轩开发的程序返回的,用来测试");

                String s = documentResponse.asXML();
                System.out.println(s);

                return s;
            } catch (DocumentException | InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "success";
    }
}

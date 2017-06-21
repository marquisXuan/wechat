package org.yyx.study.wechat.controller;

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
import org.yyx.study.wechat.util.ValidateWeChat;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

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

    @PostMapping("/")
    public String accessPost(HttpServletRequest request) {
        try {
            ServletInputStream inputStream = request.getInputStream();

            SAXReader saxReader = new SAXReader();
            try {
                Document document = saxReader.read(inputStream);
                Element rootElement = document.getRootElement();
                printNodeMessage(rootElement, null);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
           /* byte[] b = new byte[1024];
            int length;
            StringBuffer stringBuffer = new StringBuffer();
            while ((length = inputStream.read(b)) > 0) {
                stringBuffer.append(new String(b, 0, length));
            }

            logger.info(stringBuffer.toString());*/

        } catch (IOException e) {
            e.printStackTrace();
        }



         /*创建一个document*/
        Document document = DocumentHelper.createDocument();
        /*生成根节点*/
        Element rootElement = document.addElement("xml");
        Element toUserName = rootElement.addElement("ToUserName");
        toUserName.addCDATA("ot61CwKqk6UNlyZGYLrgOK6k1vR0");
        Element fromUserName = rootElement.addElement("FromUserName");
        fromUserName.addCDATA("gh_26725d663db1");
        Element cdata = rootElement.addElement("CreateTime");
        cdata.addCDATA(new Date().getTime() + "");
        Element MsgType = rootElement.addElement("MsgType");
        MsgType.addCDATA("text");
        Element content = rootElement.addElement("Content");
        content.addCDATA("这是手写的");

        String s = document.asXML();

        return s;
                /*"<xml><ToUserName><![CDATA[ot61CwKqk6UNlyZGYLrgOK6k1vR0]]></ToUserName>\n" +
                "<FromUserName><![CDATA[gh_26725d663db1]]></FromUserName>\n" +
                "<CreateTime>1498013819</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[5]]></Content>\n" +
                "<MsgId>6433920361987854659</MsgId>\n" +
                "</xml>";*/
    }

    private void printNodeMessage(Element element, Element parentElement) {
        /**
         * 如果当前节点是根节点,遍历根节点
         * 如果当前节点不是根节点
         *  看当前节点下是否有子节点，没有，输出信息，有遍历当前节点
         */
        System.out.println("当前节点是不是根节点：" + (element.isRootElement() ? "是" : "不是") + "根节点，节点名称：" + element.getName());
        if (element.isRootElement()) {
            //当前节点是根节点,遍历当前节点
            Iterator iterator = element.elementIterator();
            if (iterator.hasNext()) {
                while (iterator.hasNext()) {
                    Element next = (Element) iterator.next();
                    printNodeMessage(next, element);
                }
            }
        } else {
            //不是根节点
            //判断是否有子节点
            Iterator iterator = element.elementIterator();
            if (iterator.hasNext()) {
                //有子节点
                Element next = (Element) iterator.next();
                printNodeMessage(next, element);
            } else {
                //没有子节点
                System.out.println("节点名：" + element.getName());
                System.out.println("节点值：" + parentElement.elementText(element.getQName()));
            }
        }
    }
}

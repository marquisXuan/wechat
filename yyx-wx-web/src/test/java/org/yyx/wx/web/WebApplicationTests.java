package org.yyx.wx.web;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

    private Logger logger = LoggerFactory.getLogger(WebApplicationTests.class);

    @Test
    public void contextLoads() {
    }

    /**
     * 解析XML数据
     *
     * @param nowElement    当前节点
     * @param parentElement 父节点
     */
    private void parseXML(Element nowElement, Element parentElement) {
        //判断当前节点是否是根节点
        if (nowElement.isRootElement()) {
            //是根节点,判断根节点下是否有子节点
            Iterator iterator = nowElement.elementIterator();
            if (iterator.hasNext()) {
                while (iterator.hasNext()) {
                    //有子节点,调用parseXML
                    Element childElement = (Element) iterator.next();
                    parseXML(childElement, nowElement);
                }
            } else {
                //没有子节点
                /*抛异常也好，返回也罢，根据自己业务做处理*/
            }
        } else {
            //不是根节点
            Iterator iterator = nowElement.elementIterator();
            if (iterator.hasNext()) {
                while (iterator.hasNext()) {
                    //有子节点,调用parseXML
                    Element childElement = (Element) iterator.next();
                    parseXML(childElement, nowElement);
                }
            } else {
                logger.info("\n当前节点名：{}\n\t节点值：{}", nowElement.getName(), parentElement.elementText(nowElement.getName()));
            }
        }
    }

    /**
     * 测试创建XML
     */
    @Test
    public void testCreateXML() {

        logger.info("生成测试。。。");
        /*创建一个document*/
        Document document = DocumentHelper.createDocument();
        /*生成根节点*/
        Element rootElement = document.addElement("xml");
        /*创建节点toUserName*/
        Element toUserName = rootElement.addElement("toUserName");
        toUserName.setText("yyx");
        /*创建节点fromUserName*/
        Element fromUserName = rootElement.addElement("fromUserName");
        fromUserName.addText("we chat");
        /*创建CDATA块*/
        Element cdata = rootElement.addElement("cdata");
        cdata.addCDATA("ni shuo shen me ");


        String s = document.asXML();
        logger.info("\n生成的xml数据：\n{}", s);

        Element rootElementParse = document.getRootElement();
        parseXML(rootElementParse, null);

    }

    /**
     * 测试解析XML
     */
    @Test
    public void testParse() {
        logger.info("解析测试。。。");
        /*来自微信公众号发送的消息*/
        String sourceString = "<xml>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[text]]></MsgType>\n" +
                " <Content><![CDATA[this is a test]]></Content>\n" +
                " <MsgId>1234567890123456</MsgId>\n" +
                " </xml>";
        try {
            /*从字符串中创建一个Document*/
            Document document = DocumentHelper.parseText(sourceString);
            /*获取Document中的根节点*/
            Element rootElement = document.getRootElement();
            /*解析xml*/
            parseXML(rootElement, null);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}

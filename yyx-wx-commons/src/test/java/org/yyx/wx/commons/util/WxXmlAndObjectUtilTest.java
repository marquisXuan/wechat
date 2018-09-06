package org.yyx.wx.commons.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.yyx.wx.commons.vo.pubnum.reponse.message.TextMessageResponse;
import org.yyx.wx.commons.vo.pubnum.request.message.ImageMessageRequest;

public class WxXmlAndObjectUtilTest {

    @Test
    public void xmlToObject() throws InstantiationException, IllegalAccessException {
        /*创建一个document*/
        Document document = DocumentHelper.createDocument();
        /*生成根节点*/
        Element rootElement = document.addElement("xml");
        Element toUserName = rootElement.addElement("ToUserName");
        toUserName.addCDATA("oU5Vw1r_zNmmd-JmtKruHglxI82Y");
        Element fromUserName = rootElement.addElement("FromUserName");
        fromUserName.addCDATA("gh_3a75f8c2f293");
        Element cdata = rootElement.addElement("CreateTime");
        cdata.addCDATA(System.currentTimeMillis() + "");
        Element MsgType = rootElement.addElement("MsgType");
        MsgType.addCDATA("text");
        Element content = rootElement.addElement("Content");
        content.addCDATA("这是手写的");

        ImageMessageRequest baseMessage = WxXmlAndObjectUtil.xmlToObject(rootElement, ImageMessageRequest.class);
        System.out.println(baseMessage + ": " + baseMessage.getFromUserName());
    }

    @Test
    public void testObjectToXml() throws IllegalAccessException {
        TextMessageResponse baseMessage = new TextMessageResponse();
        baseMessage.setContent("1111");
        baseMessage.setCreateTime(System.currentTimeMillis());
        baseMessage.setFromUserName("123");
        baseMessage.setMsgId(1);
        String s = WxXmlAndObjectUtil.objectToxml(baseMessage);
        System.out.println(s);
    }
}
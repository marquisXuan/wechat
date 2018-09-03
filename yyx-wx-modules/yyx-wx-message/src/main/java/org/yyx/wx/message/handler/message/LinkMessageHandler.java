package org.yyx.wx.message.handler.message;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.reponse.message.LinkMessageResponse;
import org.yyx.wx.commons.vo.pubnum.request.message.LinkMessageRequest;
import org.yyx.wx.message.handler.AbstractMessageHandler;

/**
 * 链接消息处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-19:45
 */
public class LinkMessageHandler extends AbstractMessageHandler {
    /**
     * 对象
     */
    private static final LinkMessageHandler LINK_MESSAGE_HANDLER = new LinkMessageHandler();
    /**
     * TextMessageHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkMessageHandler.class);

    /**
     * 私有构造
     */
    private LinkMessageHandler() {
    }

    /**
     * 获取对象
     *
     * @return 返回对象
     */
    public static LinkMessageHandler getInstance() {
        return LINK_MESSAGE_HANDLER;
    }

    /**
     * 文本消息处理器
     *
     * @param element 实际处理器要处理的数据
     * @return 处理后的消息
     */
    @Override
    protected LinkMessageResponse dealTask(Element element) {
        LOGGER.info("[进入链接消息处理器]");
        LinkMessageRequest linkMessageRequest = this.modelMethod(element);
        // 封装返回消息实体
        LinkMessageResponse linkMessageResponse = new LinkMessageResponse();
        // 发送者是请求者对象中的接收者
        linkMessageResponse.setFromUserName(linkMessageRequest.getToUserName());
        // 接收者是请求者对象中的发送者
        linkMessageResponse.setToUserName(linkMessageRequest.getFromUserName());
        linkMessageResponse.setTitle("服务器返回消息");
        linkMessageResponse.setUrl("https://my.oschina.net/yzwjyw/");
        linkMessageResponse.setDescription("你发送了一条链接消息");
        linkMessageResponse.setMsgId(linkMessageRequest.getMsgId());
        linkMessageResponse.setCreateTime(System.currentTimeMillis());
        return linkMessageResponse;
    }

    /**
     * 获取文本消息处理器级别
     *
     * @return 文本消息处理器级别
     */
    @Override
    protected String getHandlerLevel() {
        return MessageTypeEnum.link.toString();
    }

    /**
     * 模板方法
     *
     * @param element 微信请求过来的消息:xml
     * @return xml转换之后的实体对象 有可能为空
     */
    @Override
    protected LinkMessageRequest modelMethod(Element element) {
        LOGGER.info("[微信请求过来的消息:xml格式数据] {}", element);
        LinkMessageRequest linkMessageRequest;
        try {
            linkMessageRequest = WxXmlAndObjectUtil.xmlToObject(element, LinkMessageRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        return linkMessageRequest;
    }
}

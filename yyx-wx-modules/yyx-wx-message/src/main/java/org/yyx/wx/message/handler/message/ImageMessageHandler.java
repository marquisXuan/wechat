package org.yyx.wx.message.handler.message;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.reponse.message.ImageMessageResponse;
import org.yyx.wx.commons.vo.pubnum.request.message.ImageMessageRequest;
import org.yyx.wx.message.handler.AbstractMessageHandler;

/**
 * 图片消息处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/3-14:54
 */
public class ImageMessageHandler extends AbstractMessageHandler {

    private static final ImageMessageHandler IMAGE_MESSAGE_HANDLER = new ImageMessageHandler();
    /**
     * ImageMessageHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageMessageHandler.class);

    /**
     * 私有构造
     */
    private ImageMessageHandler() {
    }

    /**
     * 返回实例
     *
     * @return 实例对象
     */
    public static ImageMessageHandler getInstance() {
        return IMAGE_MESSAGE_HANDLER;
    }

    @Override
    protected ImageMessageResponse dealTask(Element element) {
        LOGGER.info("[进入链接消息处理器]");
        ImageMessageRequest imageMessageRequest = this.modelMethod(element);
        // 封装返回消息实体
        ImageMessageResponse imageMessageResponse = new ImageMessageResponse();
        // 发送者是请求者对象中的接收者
        imageMessageResponse.setFromUserName(imageMessageRequest.getToUserName());
        // 接收者是请求者对象中的发送者
        imageMessageResponse.setToUserName(imageMessageRequest.getFromUserName());
        imageMessageResponse.setPicUrl("http://file.happyqing.com/xuan/index.jpg");
        imageMessageResponse.setMsgType(MessageTypeEnum.image.toString());
        imageMessageResponse.setMsgId(imageMessageRequest.getMsgId());
        imageMessageResponse.setCreateTime(System.currentTimeMillis());
        return imageMessageResponse;
    }

    @Override
    protected String getHandlerLevel() {
        return MessageTypeEnum.image.toString();
    }

    @Override
    protected ImageMessageRequest modelMethod(Element element) {
        LOGGER.info("[微信请求过来的消息:xml格式数据] {}", element);
        ImageMessageRequest imageMessageRequest;
        try {
            imageMessageRequest = WxXmlAndObjectUtil.xmlToObject(element, ImageMessageRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        return imageMessageRequest;
    }
}

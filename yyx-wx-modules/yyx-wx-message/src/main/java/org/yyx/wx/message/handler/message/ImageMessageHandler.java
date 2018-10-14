package org.yyx.wx.message.handler.message;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.bussinessenum.MessageTypeEnum;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.request.message.ImageMessageRequest;
import org.yyx.wx.commons.vo.pubnum.response.message.BaseMessageResponse;
import org.yyx.wx.message.handler.AbstractMessageHandler;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;
import org.yyx.wx.message.proxy.message.ImageMessageHandlerProxy;

/**
 * 图片消息处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/3-14:54
 */
public class ImageMessageHandler extends AbstractMessageHandler {
    /**
     * 创建对象
     */
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

    /**
     * 实际执行的方法
     *
     * @param element 实际处理器要处理的数据
     * @return 处理结果
     */
    @Override
    protected BaseMessageResponse dealTask(Element element) {
        LOGGER.info("[图片消息处理器开始工作....]");
        ImageMessageRequest imageMessageRequest = this.modelMethod(element);
        return baseMessageHandlerProxy.dealMessage(imageMessageRequest);
    }

    /**
     * 获取图像消息处理器级别
     *
     * @return 图像消息处理器级别
     */
    @Override
    protected String getHandlerLevel() {
        return MessageTypeEnum.image.toString();
    }

    @Override
    protected ImageMessageRequest modelMethod(Element element) {
        LOGGER.info("[微信请求过来的消息:xml格式数据] {}", element.asXML().trim());
        ImageMessageRequest imageMessageRequest;
        try {
            imageMessageRequest = WxXmlAndObjectUtil.xmlToObject(element, ImageMessageRequest.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
        return imageMessageRequest;
    }

    /**
     * 检查是否是自己的代理类
     *
     * @return true / false
     */
    @Override
    protected boolean isMineProxy(BaseMessageHandlerProxy baseMessageHandlerProxy) {
        if (baseMessageHandlerProxy instanceof ImageMessageHandlerProxy) {
            this.baseMessageHandlerProxy = baseMessageHandlerProxy;
            return true;
        }
        return false;
    }
}

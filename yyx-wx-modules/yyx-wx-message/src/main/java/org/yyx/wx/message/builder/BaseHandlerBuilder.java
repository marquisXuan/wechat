package org.yyx.wx.message.builder;

import org.yyx.wx.message.handler.AbstractMessageHandler;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;

/**
 * 建造者抽象类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/16-08:52
 */
public abstract class BaseHandlerBuilder {

    /**
     * 创建一个默认链条，包含默认处理链和自定义处理链
     *
     * @param abstractMessageHandlers   自定义消息处理链数组
     * @param baseMessageHandlerProxies 自定义处理链顺序
     * @return 封装好的处理链
     */
    public abstract AbstractMessageHandler getDefaultAndCustomerHandler(final AbstractMessageHandler[] abstractMessageHandlers, BaseMessageHandlerProxy[] baseMessageHandlerProxies);

    /**
     * 创建一个默认链条，不包含自定义处理链
     * 默认链条顺序为：
     * <p>
     * <p>
     * * 未关注扫描二维码 -> 关注过扫描二维码 -> 订阅[关注] -> 文本消息
     * * -> 链接消息处理器 -> 图片消息处理器 -> 语音消息处理器 -> 小视频消息处理器
     * * -> 视频消息处理器 -> 地理位置消息处理器 -> 取消订阅[关注]公众号事件处理器 -> 模板消息推送事件处理器
     * *
     *
     * @param baseMessageHandlerProxies 自定义处理链顺序
     * @return 封装好的处理链
     */
    public abstract AbstractMessageHandler getDefaultHandler(BaseMessageHandlerProxy[] baseMessageHandlerProxies);
}

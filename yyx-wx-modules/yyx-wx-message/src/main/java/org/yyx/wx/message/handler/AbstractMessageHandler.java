package org.yyx.wx.message.handler;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyx.wx.commons.exception.handler.HandlerException;
import org.yyx.wx.commons.exception.handler.NoSuitedHandlerException;
import org.yyx.wx.commons.exception.handler.OutOfOverMaxHandlerException;
import org.yyx.wx.commons.vo.pubnum.BaseMessageAndEventRequestAndResponse;
import org.yyx.wx.commons.vo.pubnum.response.message.BaseMessageResponse;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;

import static org.yyx.wx.commons.constant.HandlerConstant.MAX_HANDLER_COUNT;


/**
 * 消息处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-19:28
 */
public abstract class AbstractMessageHandler {

    /**
     * AbstractMessageHandler日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMessageHandler.class);
    /**
     * 设置最大处理器链数，防止出现超长链，破坏系统性能
     */
    private static Integer maxHandlerCount = MAX_HANDLER_COUNT;
    /**
     * 下一个消息处理器
     */
    protected AbstractMessageHandler nextHandler;
    /**
     * 业务Service
     */
    protected BaseMessageHandlerProxy baseMessageHandlerProxy;

    /**
     * 每个处理器都必须要重写的方法
     *
     * @param element 实际处理器要处理的数据
     * @return 给微信的消息实体
     */
    protected abstract BaseMessageResponse dealTask(Element element);

    /**
     * 获取该处理器的处理级别
     *
     * @return 处理级别
     */
    protected abstract String getHandlerLevel();

    /**
     * 模板方法
     * 但是由于事件处理器是一种特殊情况，所以此方法不设置为final
     * <p>
     *
     * @param baseMessageRequest 微信请求过来的消息和事件的父类
     * @return 消息
     * @throws HandlerException 处理器异常
     */
    public BaseMessageResponse handleMessage(BaseMessageAndEventRequestAndResponse baseMessageRequest
            , Element element)
            throws HandlerException {
        BaseMessageResponse baseMessage;
        String msgType = baseMessageRequest.getMsgType();
        LOGGER.info("[消息总线处理器]\n[当前微信请求的事件处理级别]：{}\n[当前处理器的处理级别是]：{}", msgType, this.getHandlerLevel());
        if (this.getHandlerLevel().equals(msgType)) {
            baseMessage = this.dealTask(element);
        } else {
            if (nextHandler != null) {
                baseMessage = this.nextHandler.handleMessage(baseMessageRequest, element);
            } else {
                LOGGER.error("[没有可以处理该类型事件的处理器]");
                throw new NoSuitedHandlerException();
            }
        }
        return baseMessage;
    }

    /**
     * 是否有下个处理器
     *
     * @return 有：true 没有：false
     */
    public boolean hasNext() {
        return nextHandler != null;
    }

    /**
     * 模板方法
     *
     * @param element 微信请求过来的消息:xml
     * @return xml转换之后的实体对象
     */
    protected abstract BaseMessageAndEventRequestAndResponse modelMethod(Element element);

    /**
     * 注入业务处理器
     *
     * @param baseMessageHandlerProxy 业务处理器
     */
    public void setBaseMessageHandlerProxy(BaseMessageHandlerProxy[] baseMessageHandlerProxy) {
        if (this.baseMessageHandlerProxy == null) {
            for (int i = 0; i < baseMessageHandlerProxy.length; i++) {
                if (isMineProxy(baseMessageHandlerProxy[i])) {
                    break;
                }
            }
        }
    }

    /**
     * 检查是否是自己的代理类
     *
     * @param baseMessageHandlerProxy 业务注入
     * @return true / false
     */
    protected abstract boolean isMineProxy(BaseMessageHandlerProxy baseMessageHandlerProxy);

    /**
     * 设置下个类型的任务处理器
     *
     * @param nextHandler 下个类型的任务处理器
     * @throws OutOfOverMaxHandlerException 当前链条超出最大链条
     */
    public void setNextHandler(AbstractMessageHandler nextHandler) {
        maxHandlerCount--;
        if (maxHandlerCount == 0) {
            throw new OutOfOverMaxHandlerException(MAX_HANDLER_COUNT - maxHandlerCount);
        }
        this.nextHandler = nextHandler;
    }
}

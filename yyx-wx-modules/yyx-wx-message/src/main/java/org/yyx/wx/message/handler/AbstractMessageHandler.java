package org.yyx.wx.message.handler;

import lombok.Setter;
import org.dom4j.Element;
import org.yyx.wx.commons.vo.pubnum.BaseMessage;


/**
 * 消息处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-19:28
 */
public abstract class AbstractMessageHandler {

    /**
     * 下一个消息处理器
     */
    @Setter
    private AbstractMessageHandler nextHandler;

    /**
     * 模板方法
     * 但是由于事件处理器是一种特殊情况，所以此方法不设置为final
     *
     * @param baseMessageRequest 处理器
     * @return 消息
     */
    public BaseMessage handleMessage(BaseMessage baseMessageRequest, Element element) {
        BaseMessage baseMessage = null;
        if (this.getHandlerLevel().equals(baseMessageRequest.getMsgType())) {
            baseMessage = this.dealTask(element);
        } else {
            // todo do something
        }
        return baseMessage;
    }

    /**
     * 获取该处理器的处理级别
     *
     * @return 处理级别
     */
    protected abstract String getHandlerLevel();

    /**
     * 每个处理器都需要处理的任务
     *
     * @param element 实际处理器要处理的数据
     * @return 给微信的消息实体
     */
    protected abstract BaseMessage dealTask(Element element);

}

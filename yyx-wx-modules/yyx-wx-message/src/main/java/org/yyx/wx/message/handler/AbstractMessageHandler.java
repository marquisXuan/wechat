package org.yyx.wx.message.handler;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.yyx.wx.commons.vo.pubnum.BaseMessageAndEvent;


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
     * 缓存工具
     */
    protected static RedisTemplate<String, Object> redisTemplate;
    /**
     * 下一个消息处理器
     */
    protected AbstractMessageHandler nextHandler;

    /**
     * 钩子函数？
     *
     * @param element 根元素
     * @return 基础信息响应
     */
    protected BaseMessageAndEvent doSomething(Element element) {
        return this.nextHandler.dealTask(element);
    }

    /**
     * 每个处理器都需要处理的任务
     *
     * @param element 实际处理器要处理的数据
     * @return 给微信的消息实体
     */
    protected abstract BaseMessageAndEvent dealTask(Element element);

    /**
     * 获取该处理器的处理级别
     *
     * @return 处理级别
     */
    protected abstract String getHandlerLevel();

    /**
     * 模板方法
     * 但是由于事件处理器是一种特殊情况，所以此方法不设置为final
     *
     * @param baseMessageRequest 处理器
     * @return 消息
     */
    public BaseMessageAndEvent handleMessage(BaseMessageAndEvent baseMessageRequest, Element element) {
        BaseMessageAndEvent baseMessage;
        String msgType = baseMessageRequest.getMsgType();
        LOGGER.info("[消息事件总线处理器-此次微信请求的事件类型为] {}", msgType);
        LOGGER.info("[当前处理器的处理级别是] {}", this.getHandlerLevel());
        if (this.getHandlerLevel().equals(msgType)) {
            baseMessage = this.dealTask(element);
        } else {
            if (nextHandler != null) {
                baseMessage = this.nextHandler.handleMessage(baseMessageRequest, element);
            } else {
                // todo do something
                LOGGER.error("[没有可以处理该类型事件的处理器]");
                return null;
            }
        }
        return baseMessage;
    }

    /**
     * 设置下个类型的任务处理器
     *
     * @param nextHandler 下个类型的任务处理器
     */
    public void setNextHandler(AbstractMessageHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        if (AbstractMessageHandler.redisTemplate == null) {
            AbstractMessageHandler.redisTemplate = redisTemplate;
        }
    }
}

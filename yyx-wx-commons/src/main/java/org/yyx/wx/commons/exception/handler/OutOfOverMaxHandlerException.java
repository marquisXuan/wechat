package org.yyx.wx.commons.exception.handler;

/**
 * 超出最大链条异常
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/6-14:04
 */
public class OutOfOverMaxHandlerException extends HandlerException {
    /**
     *
     */
    private static final long serialVersionUID = -1047314831294036207L;

    public OutOfOverMaxHandlerException(int count) {
        super("当前链条数已超出最大链条数,已使用链条数：" + count);
    }
}

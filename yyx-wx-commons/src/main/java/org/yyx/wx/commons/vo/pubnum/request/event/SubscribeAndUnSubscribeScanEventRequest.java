package org.yyx.wx.commons.vo.pubnum.request.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 关注与未关注扫码事件请求
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/26-00:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class SubscribeAndUnSubscribeScanEventRequest extends BaseEventRequest {

    /**
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    private String EventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String Ticket;
}

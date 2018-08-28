package org.yyx.wx.commons.vo.pubnum.request.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 扫描带参数二维码事件请求
 * <p>
 * 用户未关注时，进行关注后的事件推送 Event为subscribe，EventKey为事件KEY值，qrscene_为前缀，后面为二维码的参数值
 * 用户已关注时的事件推送，Event为SCAN，EventKey	事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
 * </p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/26-00:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
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

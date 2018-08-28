package org.yyx.wx.commons.vo.pubnum.request.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 上报地理位置事件
 * <p>
 * 用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，
 * 或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置。上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL。
 * Event	事件类型，LOCATION
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/27-14:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SubLocationEventRequest extends BaseEventRequest {
    /**
     * 地理位置纬度
     */
    private long Latitude;
    /**
     * 地理位置经度
     */
    private long Longitude;
    /**
     * 地理位置精度
     */
    private long Precision;
}

package org.yyx.wx.commons.vo.pubnum.response.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 地理位置消息
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 16:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LocationMessageResponse extends BaseMessageResponse {
    /**
     * 地理位置维度
     */
    private String Location_X;
    /**
     * 地理位置经度
     */
    private String Location_Y;
    /**
     * 地图缩放大小
     */
    private String Scale;
    /**
     * 地理位置信息
     */
    private String Label;

}

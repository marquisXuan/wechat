package org.yyx.wx.commons.vo.pubnum.request.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 多媒体消息父类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/27-13:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class MultiMediaRequest extends BaseMessageRequest {
    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaID;
}
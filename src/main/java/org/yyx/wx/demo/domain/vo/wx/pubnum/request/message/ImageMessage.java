package org.yyx.wx.demo.domain.vo.wx.pubnum.request.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片消息类
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 16:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ImageMessage extends BaseMessage {
    /**
     * 图片链接（由系统生成）
     */
    private String PicUrl;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;
}

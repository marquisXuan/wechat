package org.yyx.wx.commons.vo.pubnum.request.message;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视频消息
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 16:45
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class VideoMessage extends BaseMessage {

    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String ThumbMediaId;
}

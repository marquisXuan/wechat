package org.yyx.wx.demo.domain.vo.wx.pubnum.request.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 语音消息
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 16:45
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VoiceMessage extends BaseMessage {

    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaID;
    /**
     * 语音格式，如amr，speex等
     */
    private String Format;
}

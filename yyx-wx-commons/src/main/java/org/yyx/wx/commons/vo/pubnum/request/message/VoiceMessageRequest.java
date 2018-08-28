package org.yyx.wx.commons.vo.pubnum.request.message;

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
public class VoiceMessageRequest extends MultiMediaRequest {

    /**
     * 语音格式，如amr，speex等
     */
    private String Format;

    /**
     * 语音识别结果，UTF8编码
     * 开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recognition字段
     * 由于客户端缓存，开发者开启或者关闭语音识别功能，对新关注者立刻生效，对已关注用户需要24小时生效
     */
    private String Recognition;
}

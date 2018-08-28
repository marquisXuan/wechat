package org.yyx.wx.commons.vo.pubnum.request.message;

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
public class ImageMessageRequest extends MultiMediaRequest {
    /**
     * 图片链接（由系统生成）
     */
    private String PicUrl;

}

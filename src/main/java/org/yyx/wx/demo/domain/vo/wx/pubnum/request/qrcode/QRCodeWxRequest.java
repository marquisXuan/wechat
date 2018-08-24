package org.yyx.wx.demo.domain.vo.wx.pubnum.request.qrcode;

import lombok.Data;

/**
 * 微信二维码请求实体
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-13:29
 */
@Data
public class QRCodeWxRequest {

    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     */
    private long expire_seconds = 30L;

    /**
     * 二维码类型
     */
    private QRCodeTypeEnum action_name;
    /**
     * 二维码详细信息
     */
    private ActionInfoWxRequest action_info;

    /**
     * 二维码类型
     */
    public enum QRCodeTypeEnum {
        /**
         * 临时的整型参数值
         */
        QR_SCENE,
        /**
         * 临时的字符串参数值
         */
        QR_STR_SCENE,
        /**
         * 永久的整型参数值
         */
        QR_LIMIT_SCENE,
        /**
         * 永久的字符串参数值
         */
        QR_LIMIT_STR_SCENE
    }
}

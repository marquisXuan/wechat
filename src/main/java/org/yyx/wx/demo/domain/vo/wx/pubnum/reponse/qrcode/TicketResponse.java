package org.yyx.wx.demo.domain.vo.wx.pubnum.reponse.qrcode;

import lombok.Data;

/**
 * 微信二维码返回值
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-13:53
 */
@Data
public class TicketResponse {

    /**
     * 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     */
    private String ticket;
    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）。
     */
    private long expire_seconds;
    /**
     * 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     */
    private String url;
}

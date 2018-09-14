package org.yyx.wx.acount.qrcode.service;


import org.yyx.wx.commons.vo.pubnum.request.qrcode.TicketRequest;
import org.yyx.wx.commons.vo.pubnum.request.qrcode.QRCodeWxRequest;

/**
 * 微信二维码业务接口
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-13:22
 */
public interface IQRCodeService {
    /**
     * 创建整型永久的二维码Ticket
     *
     * @param qrCodeWxRequest 微信二维码请求实体
     * @return Ticket
     */
    TicketRequest createIntPermanentTicket(QRCodeWxRequest qrCodeWxRequest);

    /**
     * 获取整型临时二维码Ticket
     *
     * @param qrCodeWxRequest 微信二维码请求实体
     * @return Ticket
     */
    TicketRequest createIntTempTicket(QRCodeWxRequest qrCodeWxRequest);

    /**
     * 创建字符串形式永久的二维码Ticket
     *
     * @param qrCodeWxRequest 微信二维码请求实体
     * @return Ticket
     */
    TicketRequest createStrPermanentTicket(QRCodeWxRequest qrCodeWxRequest);

    /**
     * 创建字符串形式的临时二维码Ticket
     *
     * @param qrCodeWxRequest 微信二维码请求实体
     * @return Ticket
     */
    TicketRequest createStrTempTicket(QRCodeWxRequest qrCodeWxRequest);
}

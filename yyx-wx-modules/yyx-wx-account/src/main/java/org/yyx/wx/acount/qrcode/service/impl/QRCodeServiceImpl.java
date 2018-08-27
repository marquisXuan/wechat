package org.yyx.wx.acount.qrcode.service.impl;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.yyx.wx.acount.qrcode.config.WxQRCodeConfig;
import org.yyx.wx.acount.qrcode.service.IQRCodeService;
import org.yyx.wx.commons.vo.pubnum.reponse.BaseAccessToken;
import org.yyx.wx.commons.vo.pubnum.reponse.qrcode.TicketResponse;
import org.yyx.wx.commons.vo.pubnum.request.qrcode.QRCodeWxRequest;

import javax.annotation.Resource;

import static org.yyx.wx.commons.constant.CacheKeyConstant.ACCESS_TOKEN_NO_OPENID;


/**
 * 微信二维码业务接口实现
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-13:22
 */
@Service
public class QRCodeServiceImpl implements IQRCodeService {

    @Resource
    private RedisTemplate<String, BaseAccessToken> redisTemplate;

    /**
     * 二维码配置对象
     */
    @Resource
    private WxQRCodeConfig qrCodeConfig;

    /**
     * 创建整型永久的二维码Ticket
     *
     * @param qrCodeWxRequest 微信二维码请求实体
     * @return Ticket
     */
    @Override
    public TicketResponse createIntPermanentTicket(QRCodeWxRequest qrCodeWxRequest) {
        // QR_SCENE为临时的整型参数值
        qrCodeWxRequest.setAction_name(QRCodeWxRequest.QRCodeTypeEnum.QR_LIMIT_SCENE);
        return createTicket(qrCodeWxRequest);
    }

    /**
     * 获取整型临时二维码Ticket
     *
     * @param qrCodeWxRequest 微信二维码请求实体
     * @return Ticket
     */
    @Override
    public TicketResponse createIntTempTicket(QRCodeWxRequest qrCodeWxRequest) {
        // QR_SCENE为临时的整型参数值
        qrCodeWxRequest.setAction_name(QRCodeWxRequest.QRCodeTypeEnum.QR_SCENE);
        return createTicket(qrCodeWxRequest);
    }

    /**
     * 创建字符串形式永久的二维码Ticket
     *
     * @param qrCodeWxRequest 微信二维码请求实体
     * @return Ticket
     */
    @Override
    public TicketResponse createStrPermanentTicket(QRCodeWxRequest qrCodeWxRequest) {
        // QR_SCENE为临时的整型参数值
        qrCodeWxRequest.setAction_name(QRCodeWxRequest.QRCodeTypeEnum.QR_LIMIT_STR_SCENE);
        return createTicket(qrCodeWxRequest);
    }

    /**
     * 创建字符串形式的临时二维码Ticket
     *
     * @param qrCodeWxRequest 微信二维码请求实体
     * @return Ticket
     */
    @Override
    public TicketResponse createStrTempTicket(QRCodeWxRequest qrCodeWxRequest) {
        // QR_SCENE为临时的整型参数值
        qrCodeWxRequest.setAction_name(QRCodeWxRequest.QRCodeTypeEnum.QR_STR_SCENE);
        return createTicket(qrCodeWxRequest);
    }

    /**
     * 获取Ticket方法
     *
     * @param qrCodeWxRequest 微信二维码请求实体
     * @return Ticket
     */
    private TicketResponse createTicket(QRCodeWxRequest qrCodeWxRequest) {
        // 获取 BaseAccessToken
        BaseAccessToken baseAccessToken = redisTemplate.opsForValue().get(ACCESS_TOKEN_NO_OPENID);
        // 拼接请求创建Ticket的URL
        String urlCreateTicket = qrCodeConfig.getUrlCreateTicket() + baseAccessToken.getAccess_token();
        String qrCodeRequestBody = JSONObject.toJSONString(qrCodeWxRequest);
        String ticketResponseStr = HttpRequest
                .post(urlCreateTicket)
                .body(qrCodeRequestBody)
                .execute().body();
        TicketResponse ticketResponse = JSONObject.parseObject(ticketResponseStr, TicketResponse.class);
        return ticketResponse;
    }
}

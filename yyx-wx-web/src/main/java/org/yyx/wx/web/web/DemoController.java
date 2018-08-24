package org.yyx.wx.web.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyx.wx.acount.qrcode.config.WxQRCodeConfig;
import org.yyx.wx.acount.qrcode.service.IQRCodeService;
import org.yyx.wx.commons.vo.pubnum.reponse.AccessToken;
import org.yyx.wx.commons.vo.pubnum.reponse.qrcode.TicketResponse;
import org.yyx.wx.commons.vo.pubnum.request.qrcode.ActionInfoWxRequest;
import org.yyx.wx.commons.vo.pubnum.request.qrcode.QRCodeWxRequest;
import org.yyx.wx.acount.auth.service.IAccessTokenService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DemoController
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/24-10:44
 */
@Api(tags = "微信Demo")
@RestController
public class DemoController {

    @Resource
    private IQRCodeService iqrCodeService;
    /**
     * 获取AccessToken的业务接口
     */
    @Resource
    private IAccessTokenService accessTokenService;

    @Resource
    private WxQRCodeConfig wxQRCodeConfig;

    /**
     * 获取AccessToken测试接口
     *
     * @return AccessToken
     */
    @ApiOperation(httpMethod = "GET", value = "获取AccessToken接口")
    @GetMapping("/access/token")
    public AccessToken getAccessToken() {
        return accessTokenService.getAccessToken();
    }

    @GetMapping("/qr.img")
    @ApiOperation(httpMethod = "GET", value = "获取二维码的接口")
    public void getQRCodeDemo(HttpServletResponse response) {
        AccessToken accessToken = accessTokenService.getAccessToken();
        QRCodeWxRequest qrCodeWxRequest = new QRCodeWxRequest();
        qrCodeWxRequest.setExpire_seconds(300L);
        ActionInfoWxRequest actionInfo = new ActionInfoWxRequest();
        ActionInfoWxRequest.Scene scene = actionInfo.new Scene();
        scene.setScene_id(1);
        scene.setScene_str("叶云轩");
        actionInfo.setScene(scene);
        qrCodeWxRequest.setAction_info(actionInfo);
        TicketResponse intTempTicket = iqrCodeService.createStrTempTicket(qrCodeWxRequest);
        try {
            response.sendRedirect(wxQRCodeConfig.getUrlQRCode() + intTempTicket.getTicket());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Ticket接口
     *
     * @return Ticket
     */
    @ApiOperation(httpMethod = "GET", value = "获取Ticket接口")
    @GetMapping("/ticket")
    public TicketResponse getQRCodeTicket() {
        QRCodeWxRequest qrCodeWxRequest = new QRCodeWxRequest();
        qrCodeWxRequest.setExpire_seconds(30L);
        ActionInfoWxRequest actionInfo = new ActionInfoWxRequest();
        ActionInfoWxRequest.Scene scene = actionInfo.new Scene();
        scene.setScene_id(1);
        scene.setScene_str("123");
        actionInfo.setScene(scene);
        qrCodeWxRequest.setAction_info(actionInfo);
        return iqrCodeService.createIntTempTicket(qrCodeWxRequest);
    }
}

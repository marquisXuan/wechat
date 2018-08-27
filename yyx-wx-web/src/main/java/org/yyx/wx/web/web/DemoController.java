package org.yyx.wx.web.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyx.wx.acount.auth.service.IAccessTokenService;
import org.yyx.wx.acount.qrcode.config.WxQRCodeConfig;
import org.yyx.wx.acount.qrcode.service.IQRCodeService;
import org.yyx.wx.commons.vo.pubnum.reponse.BaseAccessToken;
import org.yyx.wx.commons.vo.pubnum.reponse.qrcode.TicketResponse;
import org.yyx.wx.commons.vo.pubnum.request.qrcode.ActionInfoWxRequest;
import org.yyx.wx.commons.vo.pubnum.request.qrcode.QRCodeWxRequest;

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
@RequestMapping("api")
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

    private QRCodeWxRequest qrCodeWxRequest = new QRCodeWxRequest();

    {
        qrCodeWxRequest.setExpire_seconds(30L);
        ActionInfoWxRequest actionInfo = new ActionInfoWxRequest();
        ActionInfoWxRequest.Scene scene = actionInfo.new Scene();
        scene.setScene_id(123);
        scene.setScene_str("test");
        actionInfo.setScene(scene);
        qrCodeWxRequest.setAction_info(actionInfo);
    }

    /**
     * 获取AccessToken测试接口
     *
     * @return BaseAccessToken
     */
    @ApiOperation(httpMethod = "GET", value = "获取AccessToken接口")
    @GetMapping("/access/token")
    public BaseAccessToken getAccessToken() {
        return accessTokenService.getBaseAccessToken();
    }

    @GetMapping("/qr.png")
    @ApiOperation(httpMethod = "GET", value = "获取二维码的接口")
    public void getQRCodeDemo(HttpServletResponse response, String userName) {
        accessTokenService.getBaseAccessToken();
        qrCodeWxRequest.setExpire_seconds(30L);
        ActionInfoWxRequest actionInfo = new ActionInfoWxRequest();
        ActionInfoWxRequest.Scene scene = actionInfo.new Scene();
        scene.setScene_id(123);
        scene.setScene_str(userName);
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
        return iqrCodeService.createIntTempTicket(qrCodeWxRequest);
    }

}
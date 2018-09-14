package org.yyx.wx.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyx.wx.acount.auth.service.IAccessTokenService;
import org.yyx.wx.acount.qrcode.config.WxQRCodeConfig;
import org.yyx.wx.acount.qrcode.service.IQRCodeService;
import org.yyx.wx.commons.vo.pubnum.request.auth.BaseAccessTokenRequest;
import org.yyx.wx.commons.vo.pubnum.request.qrcode.TicketRequest;
import org.yyx.wx.commons.vo.pubnum.request.qrcode.ActionInfoWxRequest;
import org.yyx.wx.commons.vo.pubnum.request.qrcode.QRCodeWxRequest;
import org.yyx.wx.commons.vo.pubnum.response.model.ModelMessageResponse;
import org.yyx.wx.message.service.IMessageService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
public class QRCodeController {

    /**
     * DemoController日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QRCodeController.class);
    @Resource
    private IQRCodeService iqrCodeService;
    /**
     * 获取AccessToken的业务接口
     */
    @Resource
    private IAccessTokenService accessTokenService;
    /**
     *
     */
    @Resource
    private WxQRCodeConfig wxQRCodeConfig;
    /**
     *
     */
    private QRCodeWxRequest qrCodeWxRequest = new QRCodeWxRequest();
    /**
     *
     */
    @Resource
    private IMessageService messageService;

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
     * 获取临时带参数字符串二维码的接口
     *
     * @param response 响应
     * @param userName 用户名
     */
    @GetMapping("/{userName}/qr.png")
    @ApiOperation(httpMethod = "GET", value = "获取二维码的接口")
    public void getQRCodeDemo(HttpServletResponse response, @PathVariable("userName") String userName) {
        // 获取基础AccessToken
        BaseAccessTokenRequest baseAccessTokenRequest = accessTokenService.getBaseAccessToken();
        LOGGER.info("[BaseAccessTokenRequest] {}", baseAccessTokenRequest);
        // 封装请求临时带参数字符串二维码信息
        qrCodeWxRequest.setExpire_seconds(30L);
        ActionInfoWxRequest actionInfo = new ActionInfoWxRequest();
        ActionInfoWxRequest.Scene scene = actionInfo.new Scene();
        scene.setScene_id(123);
        scene.setScene_str(userName);
        actionInfo.setScene(scene);
        qrCodeWxRequest.setAction_info(actionInfo);
        // 请求Ticket
        TicketRequest intTempTicket = iqrCodeService.createStrTempTicket(qrCodeWxRequest);
        try {
            // 跳转二维码页面
            response.sendRedirect(wxQRCodeConfig.getUrlQRCode() + intTempTicket.getTicket());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("push")
    public void testPushMessage() {
        ModelMessageResponse modelMessageResponse = new ModelMessageResponse();
        // 叶云轩的OPENID
        modelMessageResponse.setTouser("oPuhG03YiRn3KLSuiOnRufEGMFpY");
        Map<String, ModelMessageResponse.ModelDataResponse> map = new HashMap<>();

        ModelMessageResponse.ModelDataResponse firstData = modelMessageResponse.new ModelDataResponse();
        firstData.setValue("yyx-first");
        map.put("first", firstData);

        ModelMessageResponse.ModelDataResponse keyWord1Data = modelMessageResponse.new ModelDataResponse();
        keyWord1Data.setValue("yyx-key-word-1");
        map.put("keyword1", keyWord1Data);

        ModelMessageResponse.ModelDataResponse keyWord2Data = modelMessageResponse.new ModelDataResponse();
        keyWord2Data.setValue("yyx-key-word-2");
        map.put("keyword2", keyWord2Data);

        ModelMessageResponse.ModelDataResponse remarkData = modelMessageResponse.new ModelDataResponse();
        remarkData.setValue("yyx-remark");
        map.put("remark", remarkData);

        modelMessageResponse.setData(map);
        modelMessageResponse.setTemplate_id("j-997y_XEDuinwkhrcvg0bI4vh876-S27B7wrqnI7bA");
        boolean b = messageService.pushModelService(modelMessageResponse);
        if (b) {
            LOGGER.info("[success] {}", b);
        }
    }
}
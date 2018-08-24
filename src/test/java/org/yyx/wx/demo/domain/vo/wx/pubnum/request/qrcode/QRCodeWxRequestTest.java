package org.yyx.wx.demo.domain.vo.wx.pubnum.request.qrcode;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class QRCodeWxRequestTest {

    @Test
    public void createNewObject() {
        QRCodeWxRequest qrCodeWxRequest = new QRCodeWxRequest();
        qrCodeWxRequest.setExpire_seconds(30L);
        qrCodeWxRequest.setAction_name(QRCodeWxRequest.QRCodeTypeEnum.QR_SCENE);
        ActionInfoWxRequest actionInfo = new ActionInfoWxRequest();
        ActionInfoWxRequest.Scene scene = actionInfo.new Scene();
        scene.setScene_id(1);
        scene.setScene_str("123");
        actionInfo.setScene(scene);
        qrCodeWxRequest.setAction_info(actionInfo);
        String s = JSONObject.toJSONString(qrCodeWxRequest);
        System.out.println(s);
    }
}


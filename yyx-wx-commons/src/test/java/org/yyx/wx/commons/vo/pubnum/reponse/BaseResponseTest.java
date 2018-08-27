package org.yyx.wx.commons.vo.pubnum.reponse;


import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.yyx.wx.commons.vo.pubnum.reponse.qrcode.TicketResponse;

public class BaseResponseTest {
    @Test
    public void testLomBok() {
        String ticketResponseStr = "{\"errcode\":48001,\"errmsg\":\"api unauthorized hint: [nhiTya0856vr57!]\"}";
        TicketResponse ticketResponse = JSONObject.parseObject(ticketResponseStr, TicketResponse.class);
        System.out.println(ticketResponse + "  " +
                ticketResponse.getErrcode() + "  " + ticketResponse.getErrmsg());
    }

}
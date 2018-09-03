package org.yyx.wx.commons.vo.pubnum.reponse;


import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.yyx.wx.commons.vo.pubnum.reponse.qrcode.TicketResponse;

public class BaseResponseTest {


    @Test
    public void t() {
        A a = A.getInstance();
        Thread t1 = new Thread(() -> {
            a.deal3("1",1);
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            a.deal2();
        });

        Thread t2 = new Thread(() -> {
            a.deal3("2",2);
//            a.deal2();
        });
        t1.setName("thread1");
        t2.setName("thread2");
        t1.start();
        t2.start();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLomBok() {
        String ticketResponseStr = "{\"errcode\":48001,\"errmsg\":\"api unauthorized hint: [nhiTya0856vr57!]\"}";
        TicketResponse ticketResponse = JSONObject.parseObject(ticketResponseStr, TicketResponse.class);
        System.out.println(ticketResponse + "  " +
                ticketResponse.getErrcode() + "  " + ticketResponse.getErrmsg());
    }

}

class A {
    private static final A a = new A();

    private String aStr;

    private A() {
    }

    public static A getInstance() {
        return a;
    }

    // set
    public void deal(String aStr) {
        this.aStr = aStr;
        System.out.println(Thread.currentThread().getName() + " deal：" + this.aStr);
    }

    public void deal2() {
        System.out.println(Thread.currentThread().getName() + " deal2 " + this.aStr);
    }

    public void deal3(String aStr, int i) {
        System.out.println(Thread.currentThread().getName() + " deal3-1：" + aStr);
        if(1==i){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " deal3-2：" + aStr);
    }
}
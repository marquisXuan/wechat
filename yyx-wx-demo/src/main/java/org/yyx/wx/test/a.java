package org.yyx.wx.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/12-17:45
 */
public class a {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("http://yyx.sunlinka.com", "utf-8");
        System.out.println(encode);
    }
}

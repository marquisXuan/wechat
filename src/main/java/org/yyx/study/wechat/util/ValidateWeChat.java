package org.yyx.study.wechat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yyx.study.config.WeChatConfig;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 验证工具类
 * Created by 叶云轩 on 2017/6/20-11:50
 * Concat ycountjavaxuan@outlook.com
 */
@Component
public class ValidateWeChat {

    private Logger logger = LoggerFactory.getLogger(ValidateWeChat.class);
    /**
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @Resource
    private WeChatConfig weChatConfig;

    /**
     * 对参数进行排序与加密
     *
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     */
    private String sortParams(String timestamp, String nonce) {
        logger.info("排序....");
        String[] array = new String[]{weChatConfig.getDeafultAccessToken(), timestamp, nonce};
        StringBuffer sb = new StringBuffer();
        // 字符串排序
        Arrays.sort(array);
        for (int i = 0, length = array.length; i < length; i++) {
            sb.append(array[i]);
        }
        String sortString = sb.toString();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(sortString.getBytes());
            byte[] digest = md.digest();
            StringBuffer hexstr = new StringBuffer();
            String shaHex;
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            logger.info("排序完成...");
            return hexstr.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean validate(String signature, String timestamp, String nonce) {
        String s = sortParams(timestamp, nonce);
        return signature.equals(s);
    }

}

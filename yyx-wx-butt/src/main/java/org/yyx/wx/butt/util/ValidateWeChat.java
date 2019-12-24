package org.yyx.wx.butt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yyx.wx.butt.properties.DefaultPublicNumberProperties;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.yyx.wx.commons.constant.EncryptConstant.SHA_1;

/**
 * 验证工具类
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 16:16
 */
@Component
public class ValidateWeChat {

    /**
     * ValidateWeChat日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateWeChat.class);
    /**
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    @Resource
    private DefaultPublicNumberProperties defaultPublicNumberProperties;

    /**
     * 对参数进行排序与加密
     * <p>
     * 将token、timestamp、nonce三个参数进行字典序排序
     *
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     */
    private String sortAndEncryptParams(String timestamp, String nonce) {
        // 获取微信服务器配置的Token信息
        String configToken = defaultPublicNumberProperties.getConfigToken();
        LOGGER.info("[将token、timestamp、nonce三个参数进行字典序排序] {}，{}，{}", configToken, timestamp, nonce);
        String[] array = new String[]{configToken, timestamp, nonce};
        StringBuilder stringBuilder = new StringBuilder();
        // 字符串排序
        Arrays.sort(array);
        for (String s : array) {
            stringBuilder.append(s);
        }
        String sortedString = stringBuilder.toString();
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance(SHA_1);
        } catch (NoSuchAlgorithmException e) {
            // 理论上是环境问题，没有找到这样的算法
            LOGGER.error("[没有找到 {} 算法] {}", SHA_1, e.getMessage());
            return null;
        }
        messageDigest.update(sortedString.getBytes());
        byte[] digest = messageDigest.digest();
        StringBuilder hexStringBuilder = new StringBuilder();
        String shaHex;
        for (byte b : digest) {
            shaHex = Integer.toHexString(b & 0xFF);
            if (shaHex.length() < 2) {
                hexStringBuilder.append(0);
            }
            hexStringBuilder.append(shaHex);
        }
        LOGGER.info("[字典排序与加密完成]");
        return hexStringBuilder.toString();
    }

    /**
     * 通过检验signature对请求进行校验
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return 返回signature与密文的对比结果
     */
    public boolean validate(String signature, String timestamp, String nonce) {
        // 排序并加密
        String s = sortAndEncryptParams(timestamp, nonce);
        // 判断是否来自微信
        return signature.equals(s);
    }
}

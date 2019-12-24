package com.cjwy.wxframework.validate.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.cjwy.wxframework.validate.domain.constant.EncryptConstant.SHA_1;

/**
 * 验证工具类
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 16:16
 */
@Slf4j
public class UtilValidateWeChat {

    /**
     * 通过检验signature对请求进行校验
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param token     微信公众号服务器配置中设置的 token
     * @return 返回signature与密文的对比结果
     */
    public static boolean validate(String signature, String timestamp, String nonce, String token) {
        // 排序并加密
        String s = sortAndEncryptParams(timestamp, nonce, token);
        // 判断是否来自微信
        return signature.equals(s);
    }

    /**
     * 对参数进行排序与加密
     * <p>
     * 将token、timestamp、nonce三个参数进行字典序排序
     *
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @param token     微信公众号服务器配置中设置的 token
     */
    private static String sortAndEncryptParams(String timestamp, String nonce, String token) {
        log.info("[将token、timestamp、nonce三个参数进行字典序排序] {}，{}，{}", token, timestamp, nonce);
        String[] array = new String[]{token, timestamp, nonce};
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
            log.error("[没有找到 {} 算法] {}", SHA_1, e.getMessage());
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
        log.info("[字典排序与加密完成]");
        return hexStringBuilder.toString();
    }
}

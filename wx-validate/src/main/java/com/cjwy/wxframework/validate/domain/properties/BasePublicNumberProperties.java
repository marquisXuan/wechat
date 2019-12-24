package com.cjwy.wxframework.validate.domain.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static com.cjwy.wxframework.validate.domain.constant.PropertiesPrefixConstant.WX_PUBLIC_NUMBER_YML_KEY;

/**
 * 基础的公众号配置属性获取
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/20 10:35 上午
 */
@Data
@ConfigurationProperties(prefix = WX_PUBLIC_NUMBER_YML_KEY)
public class BasePublicNumberProperties {
    /**
     * 公众号开发信息中的 appId 字段
     */
    private String appId;
    /**
     * 公众号开发信息中的 appSecret 字段
     */
    private String appSecret;
    /**
     * 服务器配置中的 token 字段
     */
    private String token;
    /**
     * 消息加密 默认为明文模式 即 messageDecrypt=false
     */
    private boolean messageDecrypt = false;
    /**
     * 消息加解密密钥
     * 如果 messageDecrypt = true 此值不能为空
     * 允许填写两个密钥
     */
    private List<String> encodingAESKey;
}

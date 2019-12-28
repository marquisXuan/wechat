package com.cjwy.wxframework.authorization.domain.constant;

import com.cjwy.wxframework.authorization.domain.properties.AuthWxProperties;
import com.cjwy.wxframework.authorization.domain.properties.ProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.cjwy.projects.commons.string.constant.StringConstant.NULL_STRING;
import static com.cjwy.wxframework.authorization.domain.constant.APIConstant.REDIRECT_CONTROLLER_MAPPING;
import static com.cjwy.wxframework.authorization.domain.constant.APIConstant.REDIRECT_METHOD_MAPPING;

/**
 * 微信公众号授权认证常量
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 11:13 上午
 */
@Slf4j
@Component
public class AuthWxConstant {

    /**
     * 重定向 URI
     */
    private static final String REDIRECT_URI = REDIRECT_CONTROLLER_MAPPING + REDIRECT_METHOD_MAPPING;
    /**
     * 项目配置
     */
    private static ProjectProperties projectProperties;
    /**
     * Spring 环境
     */
    private static Environment environment;
    /**
     * 微信配置
     */
    private static AuthWxProperties authWxProperties;

    /**
     * 私有变量
     */
    private AuthWxConstant() {

    }

    /**
     * 获取授权方式
     *
     * @return 响应+授权方式
     */
    public static String getResponseTypeAndScope() {
        return "&response_type=code&scope=" + getScope();
    }

    public static String getScope() {
        boolean silence = projectProperties.isSilence();
        if (silence) {
            // 静默授权
            return authWxProperties.getSilenceScope();
        } else {
            return authWxProperties.getSilenceNo();
        }
    }

    /**
     * 获取重定向地址
     *
     * @return 编码化的重定向坡地
     */
    public static String getRedirectUri() {
        String projectUri = environment.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(projectUri)) {
            projectUri = "";
        }
        // http://xxxx.com/projectName/apiControoler/apiMethod
        String domain = projectProperties.getDomain();
        String url = domain + projectUri + REDIRECT_URI;
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("[getRedirectUri] -> [序列化请求路径异常] {}", url);
        }
        return NULL_STRING;
    }

    public static ProjectProperties getProjectProperties() {
        return projectProperties;
    }

    /**
     * 静态注入项目配置
     *
     * @param projectProperties 项目配置类
     */
    @Resource
    public void setProjectProperties(ProjectProperties projectProperties) {
        AuthWxConstant.projectProperties = projectProperties;
    }

    public AuthWxProperties getAuthWxProperties() {
        return authWxProperties;
    }

    @Resource
    public void setAuthWxProperties(AuthWxProperties authWxProperties) {
        AuthWxConstant.authWxProperties = authWxProperties;
    }

    public Environment getEnvironment() {
        return environment;
    }

    @Resource
    public void setEnvironment(Environment environment) {
        AuthWxConstant.environment = environment;
    }
}
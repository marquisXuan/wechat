package com.cjwy.projects.commons.http.utils;

import com.cjwy.projects.commons.http.domain.enumm.ApiResponseEnum;
import com.cjwy.projects.commons.http.exception.NoAuthenticationException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * UtilRequest
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/31 3:04 上午
 */
public class UtilRequest {

    private UtilRequest() {
    }

    /**
     * 从请求头中获取认证信息的方法
     *
     * @param request 请求
     * @return token
     */
    public static String getAuthorizationInHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            throw new NoAuthenticationException(ApiResponseEnum.authentication_no);
        }
        return authorization;
    }

}

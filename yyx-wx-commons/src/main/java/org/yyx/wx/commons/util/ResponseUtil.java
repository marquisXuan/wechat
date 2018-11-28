package org.yyx.wx.commons.util;

import org.yyx.domain.ResponseEntity;
import org.yyx.wx.commons.vo.pubnum.request.BaseRequest;

import static org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx.getCode;
import static org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx.isSuccess;

/**
 * 返回结果工具类
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/10/19-23:05
 */
public class ResponseUtil {


    /**
     * 私有构造
     */
    private ResponseUtil() {
    }

    /**
     * 响应的封装
     *
     * @param baseRequest 微信请求本地服务器父类
     * @return 封装结果
     */
    public static ResponseEntity response(BaseRequest baseRequest) {
        Integer errcode = baseRequest.getErrcode();
        boolean success = isSuccess(errcode);
        if (success) {
            return org.yyx.util.http.ResponseUtil.success(baseRequest);
        }
        return new ResponseEntity(Long.valueOf(errcode), getCode(errcode).getDescription(), baseRequest.getErrmsg());
    }
}

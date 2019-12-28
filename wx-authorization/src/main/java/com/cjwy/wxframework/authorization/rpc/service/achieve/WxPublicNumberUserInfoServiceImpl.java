package com.cjwy.wxframework.authorization.rpc.service.achieve;

import com.alibaba.fastjson.JSONObject;
import com.cjwy.projects.commons.wx.pubnum.domain.entity.ResponseWxUserInfoEntity;
import com.cjwy.wxframework.authorization.rpc.service.WxPublicNumberUserInfoService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/28 1:55 下午
 */
@Slf4j
public class WxPublicNumberUserInfoServiceImpl implements WxPublicNumberUserInfoService {

    @Override
    public String generateTokenByWxPublicNumberUserInfo(ResponseWxUserInfoEntity responseWxUserInfoEntity) {
        log.info("[generateTokenByWxPublicNumberUserInfo] -> [框架中默认的用户信息器] {}", JSONObject.toJSONString(responseWxUserInfoEntity));
        return "1";
    }
}

package com.cjwy.wxframework.authorization.domain.condition;

import com.cjwy.wxframework.authorization.rpc.service.WxPublicNumberUserInfoService;
import com.cjwy.wxframework.authorization.rpc.service.achieve.WxPublicNumberUserInfoServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信公众号业务处理实现类条件生成器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/28 2:07 下午
 */
@Component
public class WxUserInfoServiceCondition {

    /**
     * 没有WxPublicNumberUserInfoService时生成框架中默认的一个
     *
     * @return WxPublicNumberUserInfoService
     */
    @Bean("wxPublicNumberUserInfoService")
    @ConditionalOnMissingBean(name = "wxPublicNumberUserInfoService")
    public WxPublicNumberUserInfoService wxPublicNumberUserInfoService() {
        return new WxPublicNumberUserInfoServiceImpl();
    }
}

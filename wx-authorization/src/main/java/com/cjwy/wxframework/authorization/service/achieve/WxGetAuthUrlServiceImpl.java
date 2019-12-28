package com.cjwy.wxframework.authorization.service.achieve;

import com.cjwy.projects.commons.string.utils.UtilString;
import com.cjwy.wxframework.authorization.domain.constant.AuthWxConstant;
import com.cjwy.wxframework.authorization.domain.properties.AuthWxProperties;
import com.cjwy.wxframework.authorization.service.WxGetAuthUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 微信认证授权的业务接口实现
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/27 9:42 下午
 */
@Slf4j
@Service
public class WxGetAuthUrlServiceImpl implements WxGetAuthUrlService {

    /**
     * 微信授权认证的配置
     */
    @Resource
    private AuthWxProperties authWxProperties;

    /**
     * 设置自定义state 的授权 URL 链接
     *
     * @param state state
     * @return url
     */
    @Override
    public String getWxAuthUrl(String state) {
        // https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=
        String requestCode = authWxProperties.getRequestCode();
        // http://xxx.com/projectName/controller/mthod
        String redirectUri = AuthWxConstant.getRedirectUri();
        // &response_type=code&scope=SCOPE
        String scope = AuthWxConstant.getResponseTypeAndScope();
        String statePrefix = "&state=";
        // &state=STATE#wechat_redirect
        if (StringUtils.isEmpty(state)) {
            state = UtilString.randomUUID();
        }
        state += "#wechat_redirect";
        String url = requestCode + redirectUri + scope + statePrefix + state;
        log.info("[getWxAuthUrl] -> [生成的授权连接] {}", url);
        return url;
    }

    /**
     * 设置随机义state 的授权 URL 链接
     *
     * @return url
     */
    @Override
    public String getWxAuthUrl() {
        return getWxAuthUrl(null);
    }
}

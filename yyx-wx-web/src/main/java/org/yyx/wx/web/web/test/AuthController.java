package org.yyx.wx.web.web.test;

import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yyx.wx.acount.auth.config.WxPublicNumAuthConfig;
import org.yyx.wx.acount.auth.service.IAccessTokenService;
import org.yyx.wx.commons.bussinessenum.AuthScopeEnum;
import org.yyx.wx.commons.vo.pubnum.reponse.BaseAccessToken;
import org.yyx.wx.message.websocket.WebSocketUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权Controller
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/26-02:21
 */
@RestController
@RequestMapping("api")
@CrossOrigin
public class AuthController {
    /**
     * AuthController日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    @Resource
    private WxPublicNumAuthConfig wxPublicNumAuthConfig;
    @Resource
    private IAccessTokenService accessTokenService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("user/info")
    public String getUserInfo() {
        // 基础AccessToken
        BaseAccessToken baseAccessToken = accessTokenService.getBaseAccessToken();
        // https://api.weixin.qq.com/cgi-bin/user/info?access_token=
        // ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        String userInfoUrl =
                wxPublicNumAuthConfig.getUrlBaseUserInfo()
                        + baseAccessToken.getAccess_token() + "&openid="
                        + "oPuhG03YiRn3KLSuiOnRufEGMFpY"
                        + "&lang=zh_CN";
        LOGGER.info("[查询用户信息URL] {}", userInfoUrl);
        String userInfoStr = HttpUtil.get(userInfoUrl);
        LOGGER.info("[用户信息] {}", userInfoStr);
        return userInfoStr;
    }

    /**
     * 静默授权接口
     * APPID&redirect_uri=REDIRECT_URI
     * &response_type=code
     * &scope=SCOPE
     * &state=STATE#wechat_redirect
     * <p>
     * 此处我将state用作唯一用户名来处理，用在缓存AccessToken
     *
     * @param scope auto 静默授权
     */
    @GetMapping("code/{scope}/{state}")
    public void requestCodeSilent(HttpServletResponse response,
                                  @PathVariable("scope") String scope, @PathVariable("state") String state) {
        String redirectUri = wxPublicNumAuthConfig.getRedirectUri();
        String appID = wxPublicNumAuthConfig.getAppID();
        String paramScope;
        switch (scope) {
            case "auto":
                paramScope = AuthScopeEnum.snsapi_base.toString();
                break;
            default:
                paramScope = AuthScopeEnum.snsapi_userinfo.toString();
                break;
        }
        LOGGER.info("[请求code]");
        String url =
                wxPublicNumAuthConfig.getUrlAuthCode() + appID
                        + "&redirect_uri=" + redirectUri
                        + "&response_type=code"
                        // 静默授权
                        + "&scope=" + paramScope
                        + "&state="
                        // 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
                        + state
                        + "#wechat_redirect";
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用Code获取AuthAccessToken的接口
     *
     * @param code  微信返回的code
     *              code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
     * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     */
    @GetMapping("auth/token")
    public void useCodeGetAuthAccessToken(String code, String state) {
        LOGGER.info("[微信返回code码] {} [state] {}", code, state);
        accessTokenService.getAuthAccessToken(code, state);
        try {
            WebSocketUtil.sendMessageToUser(state, "user_info");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

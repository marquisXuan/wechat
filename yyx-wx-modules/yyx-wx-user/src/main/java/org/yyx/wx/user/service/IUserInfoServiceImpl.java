package org.yyx.wx.user.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yyx.wx.acount.auth.service.IAccessTokenService;
import org.yyx.wx.commons.exception.token.AccessTokenException;
import org.yyx.wx.commons.exception.user.NoOpenIDException;
import org.yyx.wx.commons.exception.user.WxUserException;
import org.yyx.wx.commons.vo.pubnum.request.auth.BaseAccessTokenRequest;
import org.yyx.wx.commons.vo.pubnum.request.user.BatchWxUserInfoRequest;
import org.yyx.wx.commons.vo.pubnum.request.user.WxUserInfoRequest;
import org.yyx.wx.commons.vo.pubnum.response.user.BatchUserResponse;
import org.yyx.wx.user.config.WxUserInfoConfig;

import javax.annotation.Resource;
import java.util.List;

import static org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx.getCode;
import static org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx.isSuccess;
import static org.yyx.wx.commons.bussinessenum.ResponseCodeFromWx.no_open_id;

/**
 * 用户信息业务接口实现
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/14-15:35
 */
@Service
public class IUserInfoServiceImpl implements IUserInfoService {
    /**
     * IUserInfoServiceImpl日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IUserInfoServiceImpl.class);
    /**
     * AccessToken接口
     */
    @Resource
    private IAccessTokenService accessTokenManager;
    /**
     * 微信用户相关的配置
     */
    @Resource
    private WxUserInfoConfig wxUserInfoConfig;

    /**
     * 获取用户基本信息接口
     *
     * @param openID 用户的OPENID
     * @return 用户基本信息
     * @throws NoOpenIDException    OPENID为空异常
     * @throws AccessTokenException 获取AccessToken异常
     */
    @Override
    public WxUserInfoRequest getUserInfoByOpenID(String openID) throws NoOpenIDException, AccessTokenException {
        if (StrUtil.isEmpty(openID)) {
            throw new NoOpenIDException(no_open_id);
        }
        BaseAccessTokenRequest baseAccessToken;
        try {
            baseAccessToken = accessTokenManager.getBaseAccessToken();
        } catch (AccessTokenException e) {
            LOGGER.error("[获取公众号AccessToken失败] {}", e.getMessage());
            throw new AccessTokenException();
        }
        // 获取AccessToken
        String accessToken = baseAccessToken.getAccess_token();
        // 获取请求URL
        String urlBaseUserInfo = wxUserInfoConfig.getUrlBaseUserInfo();
        // 组装URL
        String requestUrl = urlBaseUserInfo + accessToken + "&openid=" + openID + "&lang=zh_CN";
        // 响应微信请求
        String responseJsonFromWx = HttpUtil.get(requestUrl);
        // 封装成对象
        WxUserInfoRequest wxUserInfoRequest = JSONObject.parseObject(responseJsonFromWx, WxUserInfoRequest.class);
        // 获取微信响应码
        Integer errcode = wxUserInfoRequest.getErrcode();
        // 是否请求成功
        boolean success = isSuccess(errcode);
        if (success) {
            return wxUserInfoRequest;
        }
        throw new WxUserException(getCode(errcode));
    }

    /**
     * 批量获取用户基本信息 一次最多100条
     *
     * @param userResponseList 用户openId集合
     * @return 用户基本信息集合
     */
    @Override
    public BatchWxUserInfoRequest getUsersInfoByOpenIDs(List<BatchUserResponse> userResponseList) {
        String urlBatchUserInfo = wxUserInfoConfig.getUrlBatchUserInfo();
        BaseAccessTokenRequest baseAccessToken;
        try {
            baseAccessToken = accessTokenManager.getBaseAccessToken();
        } catch (AccessTokenException e) {
            LOGGER.error("[获取公众号AccessToken失败] {}", e.getMessage());
            throw new AccessTokenException();
        }
        urlBatchUserInfo += baseAccessToken.getAccess_token();
        String params = JSONObject.toJSONString(userResponseList);
        String userInfoJsonList = HttpRequest.post(urlBatchUserInfo).body(params).execute().body();
        BatchWxUserInfoRequest batchWxUserInfoRequest = JSONObject.parseObject(userInfoJsonList, BatchWxUserInfoRequest.class);
        boolean success = isSuccess(batchWxUserInfoRequest.getErrcode());
        if (success) {
            return batchWxUserInfoRequest;
        }
        throw new WxUserException(getCode(batchWxUserInfoRequest.getErrcode()));
    }
}

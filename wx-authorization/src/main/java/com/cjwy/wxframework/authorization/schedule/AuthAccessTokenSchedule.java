package com.cjwy.wxframework.authorization.schedule;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.cjwy.projects.commons.cache.service.CacheService;
import com.cjwy.projects.commons.wx.pubnum.domain.entity.ResponseByWxPubNumBaseEntity;
import com.cjwy.projects.commons.wx.pubnum.domain.vo.auth.response.AuthAccessTokenVO;
import com.cjwy.wxframework.authorization.domain.constant.WxCacheConstant;
import com.cjwy.wxframework.authorization.domain.properties.AuthWxProperties;
import com.cjwy.wxframework.authorization.service.RefreshAuthAccessTokenService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 微信认证 AccessToken 定时器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/28 8:53 下午
 */
@Component
@EnableScheduling
public class AuthAccessTokenSchedule {

    /**
     * 工作中
     */
    private static boolean working = false;
    /**
     * 缓存工具类
     */
    @Resource
    private CacheService<String, String> cacheService;
    /**
     * 微信认证配置
     */
    @Resource
    private AuthWxProperties authWxProperties;
    /**
     * AuthAccessToken 刷新业务
     */
    @Resource
    private RefreshAuthAccessTokenService refreshAuthAccessTokenService;

    /**
     * 刷新 Token 的逻辑
     * 每两小时进行一次
     */
    @Scheduled(cron = "0 0 0/2 * * ? ")
    public void refreshToken() {
        if (working) {
            // 上一个定时任务没完成,直接返回
            return;
        }
        try {
            // 设置为工作中状态
            working = true;
            // 获取缓存中所有认证授权 Key
            Set<String> keys = cacheService.likeKeys(WxCacheConstant.WX_USER_AUTH_ACCESS_TOKEN_KEY_PREFIX + "*");
            keys.stream().peek(key -> {
                String value = cacheService.getValue(key);
                AuthAccessTokenVO authAccessTokenVO = JSONObject.parseObject(value, AuthAccessTokenVO.class);
                long now = System.currentTimeMillis();
                Long createTime = authAccessTokenVO.getCreateTime();
                if (now - createTime > 2 * 60 * 1000) {
                    // 距离过期时间大于 2 分钟 不处理
                    return;
                }
                String checkAuthAccessTokenUrl = authWxProperties.getCheckAuthAccessToken();
                // 检查结果
                String checkResult = HttpUtil.get(checkAuthAccessTokenUrl + authAccessTokenVO.getAccessToken() + "&openid=" + authAccessTokenVO.getOpenid());
                ResponseByWxPubNumBaseEntity responseByWxPubNumBaseEntity = JSONObject.parseObject(checkResult, ResponseByWxPubNumBaseEntity.class);
                if (responseByWxPubNumBaseEntity.getErrcode() == 0L) {
                    // 说明 AccessToken 未过期
                    return;
                }
                // 通知 Token 刷新器 刷新 Token
                refreshAuthAccessTokenService.refreshAuthAccessTokenByRefreshToken(authAccessTokenVO);
            });
        } finally {
            working = false;
        }
    }
}

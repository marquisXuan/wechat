package com.cjwy.projects.commons.wx.pubnum.domain.vo.auth.response;

import com.cjwy.projects.commons.wx.pubnum.domain.entity.ResponseByWxPubNumBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 请求 Code2AuthAccessToken 的响应实体类
 * <p/>
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2019/12/28 - 1:04 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResponseByCode2AuthAccessTokenVO extends ResponseByWxPubNumBaseEntity {

    private String accessToken;
    private Long expiresIn;
    private String openid;
    private String refreshToken;
    private String scope;
    private String state;
    /**
     * 创建时间 为当前时间
     */
    private Long createTime;

    /**
     * 记录数据创建时间
     */
    public void recordCreateTime() {
        this.createTime = System.currentTimeMillis();
    }

    /**
     * 防止贫血模型
     *
     * @param state 有可能是业务参数
     */
    public void saveState(String state) {
        this.state = state;
    }
}

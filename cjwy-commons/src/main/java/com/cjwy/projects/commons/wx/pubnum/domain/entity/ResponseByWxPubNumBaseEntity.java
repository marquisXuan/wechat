package com.cjwy.projects.commons.wx.pubnum.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信公众号返回的响应结果实体 VO
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/28 1:18 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseByWxPubNumBaseEntity {

    private long errcode;

    private String errmsg;
}

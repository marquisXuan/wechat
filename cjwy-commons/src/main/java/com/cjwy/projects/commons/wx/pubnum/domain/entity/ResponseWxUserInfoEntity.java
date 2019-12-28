package com.cjwy.projects.commons.wx.pubnum.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResponseWxUserInfoEntity extends ResponseByWxPubNumBaseEntity {

    private String city;
    private String country;
    private String headimgurl;
    private String nickname;
    private String openid;
    private List<String> privilege;
    private String province;
    private String sex;
    private String unionid;

}

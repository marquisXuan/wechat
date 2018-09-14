package org.yyx.wx.acount.user.service;

import org.yyx.wx.commons.exception.user.NoOpenIDException;
import org.yyx.wx.commons.vo.pubnum.request.user.BatchWxUserInfoRequest;
import org.yyx.wx.commons.vo.pubnum.request.user.WxUserInfoRequest;
import org.yyx.wx.commons.vo.pubnum.response.user.BatchUserResponse;

import java.util.List;

/**
 * 用户信息业务接口
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/9/14-15:23
 */
public interface IUserInfoService {

    /**
     * 获取用户基本信息接口
     *
     * @param openID 用户的OPENID
     * @return 用户基本信息
     * @throws NoOpenIDException OPENID为空异常
     */
    WxUserInfoRequest getUserInfoByOpenID(String openID) throws NoOpenIDException;

    /**
     * 批量获取用户基本信息 一次最多100条
     *
     * @param userResponseList 用户openId集合
     * @return 用户基本信息集合
     */
    BatchWxUserInfoRequest getUsersInfoByOpenIDs(List<BatchUserResponse> userResponseList);
}
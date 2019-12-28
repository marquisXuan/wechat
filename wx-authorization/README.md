# 微信公众号授权认证模块

## 使用方法

```yml
# 在 yml 中配置以下内容即可
project:
	domain: http://yourdomain.com
	redirectPageUrl: http://yourWebDomain.com/page.html
wx:
  validate:
    appId: yourAppId
    appSecret: yourAppSecret
```

## 说明

|         配置项          |                           说明                           | 必配 |
| :---------------------: | :------------------------------------------------------: | :--: |
|     project.domain      |                        项目的域名                        | [x]  |
|     project.silence     | true: 静默授权 false: 弹出授权框  微信授权时 scope 字段  |      |
|      auth.wx.appId      |                     微信公众号 APPID                     | [x]  |
|    auth.wx.appSecret    |                   微信公众号 APPSecret                   | [x]  |
| project.redirectPageUrl | 前端项目的 url,页面需要对请求参数 code,msg,data 进行处理 | [x]  |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |
|                         |                                                          |      |



## 继承

> 如果需要自己处理微信公众号获取到的用户信息,请实现com.cjwy.wxframework.authorization.rpc.service.WxPublicNumberUserInfoService接口
>
> 在处理用户授权后获取到用户信息时,返回一个业务系统唯一标识

```java

/**
 * 微信公众号用户业务
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2019/12/28 1:50 下午
 */
public interface WxPublicNumberUserInfoService {

    /**
     * 通过微信公众号用户信息生成业务系统登陆 Token
     *
     * @param responseWxUserInfoEntity 微信公众号返回的用户信息
     * @return token
     */
    String generateTokenByWxPublicNumberUserInfo(ResponseWxUserInfoEntity responseWxUserInfoEntity);
}
```


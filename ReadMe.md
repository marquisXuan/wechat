# 微信公众号

[TOC]



## 项目目的
> 为了避免节省开发成本，现将服务端与微信对接工作封装到一个项目中，暴露接口。
>
> 引入项目中只需要配置一些微信信息，即可只专注项目的业务逻辑，其余均交给此项目来完成。
>
> 实现接口，注入服务即可使用。

## 项目目录结构

```java
|-[yyx-wx]·····································父项目，公共依赖以及方便构建
|  |
|  |-[yyx-wx-butt]·····························公众号与开发者服务器对接的模块
|  |
|  |-[yyx-wx-commons]··························公众号公共模块：配置、异常、常量、实体
|  |
|  |-[yyx-wx-demo]·····························公众号使用例子：不用引，演示如何集成到项目中
|  |
|  |-[yyx-wx-manage]···························公众号管理模块
|  |
|  |-[yyx-wx-modules]
|  |  |
|  |  |-[yyx-wx-account]·······················公众号账号管理模块
|  |  |
|  |  |-[yyx-wx-message]·······················公众号推送到开发者服务器的消息处理模块
```



## 项目使用方法
> 配置好项目环境后即可使用，modules下的每个模块可单独引用。
## 项目使用环境
- 需要自己设置微信回调接口URL，**强调：该URL不需要经过URLEncode处理。**该配置使用在网页授权时才会用到

- 需要自己设置微信APP_ID和APP_SECRET以及CONFIG_TOKEN

    - APP_ID为微信公众号的appid
    - APP_SECRET是微信公众号中的secret
    - CONFIG_TOKEN是微信公众号开发配置中的令牌(Token)

- 配置用例：
    - yml文件格式
    ```yml
    wx:
      url:
        redirect_uri: URI地址(http://domain/api/xx)
      public_number:
        app_id: appID
        app_secret: appSecret
        config_token: 开发者自定配置的token
    ```
    - properties
    ```properties
    wx.url.redirect_uri=URI地址(http://domain/api/xx)
    wx.public_number.app_id=appID
    wx.public_number.app_secret=appSecret
    wx.public_number.config_token=开发者自定配置的token
    ```

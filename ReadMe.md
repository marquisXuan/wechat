# 微信公众号便捷包

[TOC]



## 项目目的
> 在做微信公众号相关的二次开发时，开发者总是需要频繁的查看API并做一系列的对接工作，为了能使开发者更专注于业务逻辑，更高效的开发，所以诞生了便捷包项目。
>
> 便捷包项目主要将本地服务端（开发者服务器）与微信对接工作整合在一起，提供相应的服务接口，从而达到减少开发者与微信的对接工作。
>
> 开发者只需将本项目以依赖的形式引入项目中，配置一些企业独有的信息，即可正常与微信公众号通信，自己的业务需要嵌入微信公众号的，只需实现对应的接口即可，大幅度的减少开发者与微信对接的工作量。同时，便捷包提供了高度个性化功能，即不想使用便捷包默认提供的实现时，只需要继承一些类即可完成定制。

## 项目目录结构

- [微信公众号](README.md)
    - [微信公众号公用模块](./yyx-wx-commons/README.md)：主要存放常量、配置、异常以及实体等。
    - ***微信公众号模块父级***
        - [本地与微信公众号消息对接模块](./yyx-wx-modules/yyx-wx-message/README.md)：用户与公众号交互后，微信会回调开发者服务器，此模块针对微信回调事件，消息推送等做了逻辑处理。
    - [微信公众号使用案例](./yyx-wx-demo/README.md)：此模块可以不引用。主要是一个web项目。用来说明整个项目的使用方式。
    - [微信服务器与开发者服务器对接模块](yyx-wx-butt/README.md): 主要用于微信服务器向开发者服务器推送事件。
```java
|-[yyx-wx]·····································父项目，公共依赖以及方便构建
|  |
|  |-[yyx-wx-commons]··························公众号公共模块：配置、异常、常量、实体
|  |
|  |-[yyx-wx-demo]·····························公众号使用例子：主要演示如何集成到项目中
|  |
|  |-[yyx-wx-modules]
|  |  |
|  |  |-[yyx-wx-account]·······················公众号账号管理模块
|  |  |
|  |  |-[yyx-wx-message]·······················公众号推送到开发者服务器的消息处理模块
|  |  |
|  |  |-[yyx-wx-user]··························公众号用户管理模块
|  |  |
|  |-[yyx-wx-web]······························公众号与开发者服务器对接模块
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
      	# URI地址
        redirect_uri: http://domain/api/xx
      public_number:
        app_id: appID
        app_secret: appSecret
        config_token: 开发者自定配置的token
    ```
    - properties
    ```properties
    # URI地址
    wx.url.redirect_uri=http://domain/api/xx
    wx.public_number.app_id=appID
    wx.public_number.app_secret=appSecret
    wx.public_number.config_token=开发者自定配置的token
    ```
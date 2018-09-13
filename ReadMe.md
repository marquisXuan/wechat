# 微信公众号

## 项目目的
> 为了避免节省开发成本，现将服务端与微信对接工作封装到一个项目中，暴露接口。
>
> 引入项目中只需要配置一些微信信息，即可只专注项目的业务逻辑，其余均交给此项目来完成。
>
> 实现接口，注入服务即可使用，每个模块中都有每个模块对应的README文档。

## 项目目录
- [微信公众号](README.md)
    - [微信公众号公用模块](./yyx-wx-commons/README.md)：主要存放常量、配置、异常以及实体等。
    - [微信公众号模块父级](./yyx-wx-modules/README.md)
        - [本地与微信公众号消息对接模块](./yyx-wx-modules/yyx-wx-message/README.md)：用户与公众号交互后，微信会回调开发者服务器，此模块针对微信回调事件，消息推送等做了逻辑处理。
        - [本地与微信公众号用户相关对接模块](./yyx-wx-modules/yyx-wx-account/README.md)：此模块针对用户使用网页授权功能时进行了逻辑处理。
    - [微信公众号使用案例](./yyx-wx-demo/README.md)：此模块可以不引用。主要是一个web项目。用来说明整个项目的使用方式。
## 项目使用方法
> 配置好项目使用环境后即可，每个模块可单独引用，每个模块中包含自己模块的使用方法。
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
        redirect_uri: URI地址
      public_number:
        app_id: appID
        app_secret: appSecret
        config_token: 开发者自定配置的token
    ```
    - properties
    ```properties
    wx.url.redirect_uri地址
    wx.public_number.app_id=appID
    wx.public_number.app_secret=appSecret
    wx.public_number.config_token=开发者自定配置的token
    ```

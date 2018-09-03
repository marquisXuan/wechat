# 微信公众号开发

## 环境
- 使用Redis做了缓存，所以需要自行配置Redis连接。
- 需要​自己设置微信回调接口URL
- 需要自己设置微信APP_ID和APP_SECRET以及CONFIG_TOKEN

- 例：
    - yml文件格式
    ```yml
    wx:
      url:
        redirect_uri: URIEncode地址
      public_number:
        app_id: appID
        app_secret: appSecret
        config_token: 开发者自定配置的token
    ```
    - properties
    ```properties
    wx.url.redirect_uriURIEncode地址
    wx.public_number.app_id=appID
    wx.public_number.app_secret=appSecret
    wx.public_number.config_token=开发者自定配置的token
    ```

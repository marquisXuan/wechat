# yyx-wx-account 公众号管理模块

## 更新日志

### 2019-12-10

- 修改yml 默认配置，需要配置的字段为以下格式

```yaml
wx:
  pubnumm:
    appid: 微信公众号后台获取的 appId
    secret: 微信公众号后台获取的 appSecret
```

这么做的原因是可以让用户自定义配置 properties 文件,并且不与默认的配置冲突

- 修整了内部实现逻辑。按模块区分请求连接URL
# yyx-wx-demo 使用案例

> 
> 在接入指南一章中，微信服务器端会回调本地服务器URL，所以对于此模块，理应只有两个方法的接口供微信调用

## 内网穿透

上传了一个frp客户端的配置文件

```ini
[common]
server_addr = frps.happyqing.com
server_port = 7000
privilege_token = frps.happyqing.com

# 此处是一个唯一标识
[yyx]
type = http
# 本地服务的端口号，此demo项目端口是8080
local_port = 8080
# 此处与[yyx]相对应。启动后访问 yyx.frps.happyqing.com
subdomain = yyx
```

项目下载之后，下载对应的frp，修改上述文件和公众号配置文件后，指定此文件启动。即可测试。

[frp下载地址](https://github.com/fatedier/frp/releases)

```shell
./frpc -c ./frpc.ini
```




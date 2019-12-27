# 微信公众号授权认证模块

## 使用方法

```yml
# 在 yml 中配置以下内容即可
project:
	domain: http://yourdomain.com
wx:
  validate:
    appId: yourAppId
    appSecret: yourAppSecret
```

|      配置项       |                          说明                           | 必配 |
| :---------------: | :-----------------------------------------------------: | :--: |
|  project.domain   |                       项目的域名                        | [x]  |
|  project.silence  | true: 静默授权 false: 弹出授权框  微信授权时 scope 字段 |      |
|   auth.wx.appId   |                    微信公众号 APPID                     | [x]  |
| auth.wx.appSecret |                  微信公众号 APPSecret                   | [x]  |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |
|                   |                                                         |      |



## 目录结构

```txt
.
├── README.md
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── cjwy
│   │   │           └── wxframework
│   │   │               └── authorization
│   │   │                   ├── AuthorizationApplication.java
│   │   │                   ├── condition
│   │   │                   ├── controller
│   │   │                   │   ├── WxGetAuthUrlController.java
│   │   │                   │   ├── WxGetCodeRedirectController.java
│   │   │                   │   └── api
│   │   │                   │       ├── WxGetAuthUrlControllerApi.java
│   │   │                   │       └── WxGetCodeRedirectControllerApi.java
│   │   │                   ├── domain
│   │   │                   │   ├── config
│   │   │                   │   │   ├── AuthWxPropertiesConfig.java
│   │   │                   │   │   └── ProjectPropertiesConfig.java
│   │   │                   │   ├── constant
│   │   │                   │   │   ├── APIConstant.java
│   │   │                   │   │   └── AuthWxConstant.java
│   │   │                   │   └── properties
│   │   │                   │       ├── AuthWxProperties.java
│   │   │                   │       └── ProjectProperties.java
│   │   │                   ├── service
│   │   │                   │   ├── WxGetAuthUrlService.java
│   │   │                   │   └── achieve
│   │   │                   │       └── WxGetAuthUrlServiceImpl.java
│   │   │                   └── utils
│   │   └── resources
│   │       ├── META-INF
│   │       │   └── additional-spring-configuration-metadata.json
│   │       ├── banner.txt
│   │       └── config
│   │           └── application.yml
│   └── test
│       └── java
│           └── com
│               └── cjwy
│                   └── wxframework
│                       └── authorization
│                           └── AuthorizationApplicationTests.java
├── target
│   ├── authorization-0.0.1-SNAPSHOT.jar
│   ├── classes
│   │   ├── META-INF
│   │   │   └── additional-spring-configuration-metadata.json
│   │   ├── banner.txt
│   │   ├── com
│   │   │   └── cjwy
│   │   │       └── wxframework
│   │   │           └── authorization
│   │   │               ├── AuthorizationApplication.class
│   │   │               ├── controller
│   │   │               │   ├── WxGetAuthUrlController.class
│   │   │               │   ├── WxGetCodeRedirectController.class
│   │   │               │   └── api
│   │   │               │       └── WxGetAuthUrlControllerApi.class
│   │   │               ├── domain
│   │   │               │   ├── config
│   │   │               │   │   ├── AuthWxPropertiesConfig.class
│   │   │               │   │   └── ProjectPropertiesConfig.class
│   │   │               │   ├── constant
│   │   │               │   │   ├── APIConstant.class
│   │   │               │   │   └── AuthWxConstant.class
│   │   │               │   └── properties
│   │   │               │       ├── AuthWxProperties.class
│   │   │               │       └── ProjectProperties.class
│   │   │               └── service
│   │   │                   ├── WxGetAuthUrlService.class
│   │   │                   └── achieve
│   │   │                       └── WxGetAuthUrlServiceImpl.class
│   │   └── config
│   │       └── application.yml
│   ├── generated-sources
│   │   └── annotations
│   ├── generated-test-sources
│   │   └── test-annotations
│   ├── maven-archiver
│   │   └── pom.properties
│   ├── maven-status
│   │   └── maven-compiler-plugin
│   │       ├── compile
│   │       │   └── default-compile
│   │       │       ├── createdFiles.lst
│   │       │       └── inputFiles.lst
│   │       └── testCompile
│   │           └── default-testCompile
│   │               ├── createdFiles.lst
│   │               └── inputFiles.lst
│   └── test-classes
│       └── com
│           └── cjwy
│               └── wxframework
│                   └── authorization
│                       └── AuthorizationApplicationTests.class
└── wx-authorization.iml

```


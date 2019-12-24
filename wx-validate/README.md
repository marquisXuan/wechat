# 微信公众号对接验证模块

> 此模块主要用来验证微信公众号开发配置中 GET 请求验证。

## 使用方法

### 单独使用

- 在 pom 中引入 jar 包

  ```xml
  <dependency>
  	<groupId>com.cjwy.wxframework</groupId>
  	<artifactId>validate</artifactId>
    <version>自己选择</version>
  </dependency>
  ```

- 在项目启动类添加注解

  ```java
  @ComponentScans({
          @ComponentScan("com.cjwy.wxframework.validate")
  })
  ```

- 配置微信公众号开发信息，在项目的 application.yml 中配置以下内容

  ```yml
  wx:
    validate:
      token: 微信公众号后台->开发->基本配置->服务器配置->token
  ```

## 目录结构

```txt
--com.cjwy.wxframework.validate
	|-- 
```


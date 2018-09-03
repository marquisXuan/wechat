## yyx-wx-message 微信公众号消息处理模块

> 这个模块的主要功能是集成了微信公众号中的消息管理模块，但是目前只添加了明文模式，对于加密模式暂未处理。

### 接收普通消息
> 当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。

以下的每个处理器都是单例的。

#### 文本消息
> 目前接收到文本消息之后的处理逻辑为直接返回一条文本消息.
> 
> 如需实现自己的逻辑，只需要写一个文本消息的处理类继承TextMessageHandler并重写dealTask(Element element)方法即可。

##### 使用方式例子：
```java
/**
 * 自己业务的文本消息处理器
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-19:45
 */
public class CustomerTextMessageHandler extends TextMessageHandler{
    
    /**
    * 自己的业务逻辑
    * @param element 微信服务器传来的消息转换成的实体类
    * @return 任意类型的消息：文本消息、多媒体消息等自己实现
    */
    @Override
    protected BaseMessageAndEvent dealTask(Element element) { 
        // 文本消息实体 - 有可能返回空值
        TextMessageRequest textMessageRequest = this.modelMethod(element);
        // todo 此处向下，写自己的业务逻辑
        // 。。。
        // 。。。
        // 。。。
        return null;
    }
}
```
#### 关注/取消关注事件、扫描带参数二维码事件、上报地理位置事件、自定义菜单事件、图片消息、语音消息、视频消息、小视频消息、地理位置消息、链接消息均与文本消息使用方式类似。

### 注：写好的CustomerTextMessageHandler等自定义业务逻辑的消息处理器需要添加到MessageEventHandlerFactory的ABSTRACT_MESSAGE_HANDLERS数组中。使用时直接调用MessageEventHandlerFactory.getMessageHandler(redisTemplate, applicationContext);即可

```java
/**
*注：写好的CustomerTextMessageHandler等自定义业务逻辑的消息处理器需要添加到MessageEventHandlerFactory的ABSTRACT_MESSAGE_HANDLERS数组中。使用时直接调用MessageEventHandlerFactory.getMessageHandler(redisTemplate, applicationContext);即可
*/
public String accessPost(HttpServletRequest request) {
        LOGGER.info("[进入处理微信请求的方法]");
        try {
            ServletInputStream inputStream = request.getInputStream();
            SAXReader saxReader = new SAXReader();
            try {
                Document document = saxReader.read(inputStream);
                Element rootElement = document.getRootElement();
                BaseMessageAndEvent baseMessage = WxXmlAndObjectUtil.xmlToObject(rootElement, BaseMessageAndEvent.class);
                AbstractMessageHandler messageHandler = getMessageHandler(redisTemplate, null);
                // 此处是重点，此处调用之后会返回处理结果。
                BaseMessageAndEvent baseMessageResponse = messageHandler.handleMessage(baseMessage, rootElement);
                // 以下是伪代码。
                return null;
            } catch (DocumentException | InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
```


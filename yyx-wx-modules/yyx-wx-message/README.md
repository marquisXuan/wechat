## yyx-wx-message 微信消息处理模块

[TOC]



> 这个模块的主要功能是集成了微信公众号中的消息管理模块，但是目前只添加了明文模式，对于加密模式暂未处理。

### 例：接收普通消息
> 当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。

以下的每个处理器都是单例的。

#### 文本消息
> 目前接收到文本消息之后的处理逻辑为直接返回一条文本消息.
> 
> 如需实现自己的逻辑，只需要写一个文本消息的处理类继承TextMessageHandler并重写dealTask(Element element)方法即可。

##### 介入方式例子一：
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

**注：**

自定义消息、事件处理器一定要添加到*MessageEventHandlerFactory*.*ABSTRACT_MESSAGE_HANDLERS*中

**关注/取消关注事件、扫描带参数二维码事件、上报地理位置事件、自定义菜单事件、图片消息、语音消息、视频消息、小视频消息、地理位置消息、链接消息均与文本消息使用方式类似。**

**注：再次强调！写好的 *CustomerTextMessageHandler* 等自定义业务逻辑的消息处理器需要添加到*MessageEventHandlerFactory* 的 *ABSTRACT_MESSAGE_HANDLERS* 数组中。使用时直接调用*MessageEventHandlerFactory.getMessageHandler();* 即可，方法返回已经组装好处理器链的处理器对象，优先使用自定义消息处理器来处理。**

```java
    /**
     * 获取事件处理器，最大支持11个额外链条
     * 链条设置
     * 自定义消息处理器 -> 未关注扫描二维码 -> 关注过扫描二维码 -> 订阅[关注]消息处理器
     * -> 文本消息 -> 链接消息处理器 -> 图片消息处理器 -> 语音消息处理器 
     * -> 小视频消息处理器 -> 视频消息处理器 -> 地理位置消息处理器 -> 取消订阅[关注]处理器
     *
     * @return 事件处理器
     */
    public static AbstractMessageHandler getMessageHandler() {return null;}
```

##### 介入方式例子二：

> 不写处理器，只写处理器中自己业务的具体实现

具体方法：(依旧使用文本消息处理器来做例子)

创建一个类实现TextMessageHandlerProxy并重写public BaseMessageResponse dealMessage(BaseMessageAndEventRequestAndResponse baseMessageAndEventRequest)方法。

```java
public class DemoTextMessageServiceImpl implements TextMessageHandlerProxy {

    /**
     * DemoServiceImpl日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoTextMessageServiceImpl.class);

    /**
     * 自定义业务处理
     *
     * @param baseMessageAndEventRequest 微信推送过来的事件实体，可强转为对应的子类。
     * @return 返回消息
     */
    @Override
    public BaseMessageResponse dealMessage(BaseMessageAndEventRequestAndResponse baseMessageAndEventRequest) {
        // 将公众号传入的消息转换为文本消息
        TextMessageRequest textMessageRequest = (TextMessageRequest) baseMessageAndEventRequest;
        LOGGER.info("[DEMO] 自定义文本业务实现类");
        // 创建一个文本类的消息，响应给公众号
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        textMessageResponse.setMsgId(1L);
        textMessageResponse.setToUserName(baseMessageAndEventRequest.getFromUserName());
        textMessageResponse.setFromUserName(baseMessageAndEventRequest.getToUserName());
        textMessageResponse.setMsgType(MessageTypeEnum.text.toString());
        textMessageResponse.setContent("[DEMO] 叶云轩自定义文本回复。\n你发的文本消息是：" + textMessageRequest.getContent());
        // endregion
        return textMessageResponse;
    }
}
```

**注：仅需要实现*TextMessageHandlerProxy*接口即可关注自己的业务。**

### 附：处理器接口中参数与可强转的子类对应表

|               接口                |              可强转的子类               |        说明        |
| :-------------------------------: | :-------------------------------------: | :----------------: |
|      TextMessageHandlerProxy      |           TextMessageRequest            |    处理文本消息    |
|     ImageMessageHandlerProxy      |           ImageMessageRequest           |    处理图片消息    |
|      LinkMessageHandlerProxy      |           LinkMessageRequest            |    处理链接消息    |
|    LocationMessageHandlerProxy    |         LocationMessageRequest          |  处理地理位置消息  |
|   ShortVideoMessageHandlerProxy   |           VideoMessageRequest           |   处理小视频消息   |
|     VideoMessageHandlerProxy      |           VideoMessageRequest           |    处理视频消息    |
|     VoiceMessageHandlerProxy      |           VoiceMessageRequest           |    处理语音消息    |
| ModelMessagePushEventHandlerProxy |      ModelMessagePushEventRequest       |  处理模板消息事件  |
|    SubscribeEventHandlerProxy     |   SubscribeAndUnSubscribeEventRequest   |    处理关注事件    |
|  SubscribeScanEventHandlerProxy   | SubscribeAndUnSubscribeScanEventRequest | 处理已关注扫码事件 |
|   UnSubscribeEventHandlerProxy    |   SubscribeAndUnSubscribeEventRequest   |  处理取消关注事件  |
| UnSubscribeScanEventHandlerProxy  | SubscribeAndUnSubscribeScanEventRequest | 处理未关注扫码事件 |
|                                   |                                         |                    |


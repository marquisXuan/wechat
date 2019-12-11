package org.yyx.wx.butt.web.controller;

import cn.hutool.core.util.ArrayUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yyx.wx.butt.properties.PublicNumberProperties;
import org.yyx.wx.butt.util.ValidateWeChat;
import org.yyx.wx.commons.util.WxXmlAndObjectUtil;
import org.yyx.wx.commons.vo.pubnum.BaseMessageAndEventRequestAndResponse;
import org.yyx.wx.commons.vo.pubnum.response.message.BaseMessageResponse;
import org.yyx.wx.message.handler.AbstractMessageHandler;
import org.yyx.wx.message.proxy.BaseMessageHandlerProxy;
import org.yyx.wx.message.proxy.event.ModelMessagePushEventHandlerProxy;
import org.yyx.wx.message.proxy.event.SubscribeEventHandlerProxy;
import org.yyx.wx.message.proxy.event.SubscribeScanEventHandlerProxy;
import org.yyx.wx.message.proxy.event.UnSubscribeEventHandlerProxy;
import org.yyx.wx.message.proxy.event.UnSubscribeScanEventHandlerProxy;
import org.yyx.wx.message.proxy.message.ImageMessageHandlerProxy;
import org.yyx.wx.message.proxy.message.LinkMessageHandlerProxy;
import org.yyx.wx.message.proxy.message.LocationMessageHandlerProxy;
import org.yyx.wx.message.proxy.message.ShortVideoMessageHandlerProxy;
import org.yyx.wx.message.proxy.message.TextMessageHandlerProxy;
import org.yyx.wx.message.proxy.message.VideoMessageHandlerProxy;
import org.yyx.wx.message.proxy.message.VoiceMessageHandlerProxy;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.yyx.wx.commons.constant.HandlerConstant.ALL;
import static org.yyx.wx.commons.constant.HandlerConstant.CUSTOMER_HANDLER;
import static org.yyx.wx.commons.constant.HandlerConstant.DEFAULT_HANDLER;
import static org.yyx.wx.commons.constant.HandlerConstant.MAX_BASE_MESSAGE_HANDLER_PROXY;
import static org.yyx.wx.message.director.DefaultDirector.getCustomerHandler;
import static org.yyx.wx.message.director.DefaultDirector.getDefaultAndCustomerHandler;
import static org.yyx.wx.message.director.DefaultDirector.getDefaultHandler;
import static org.yyx.wx.message.handler.CustomerHandlerArray.ABSTRACT_MESSAGE_HANDLERS;

/**
 * 接入微信服务器入口
 *
 * @author 叶云轩 contact by tdg_yyx@foxmail.com
 * @date 2018/8/24 - 18:27
 */
@RestController
@RequestMapping("wx")
public class ApplicationMain {

    // region 依赖注入
    /**
     * AccessEntrance日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationMain.class);
    /**
     * 公众号相关配置
     */
    @Resource
    private PublicNumberProperties publicNumberProperties;
    /**
     * 验证工具类
     */
    @Resource
    private ValidateWeChat validateWeChat;
    /**
     * 关注事件代理
     */
    @Resource
    private SubscribeEventHandlerProxy subscribeEventHandlerProxy;
    /**
     * 用户关注时扫描二维码事件处理器
     */
    @Resource
    private SubscribeScanEventHandlerProxy subscribeScanEventHandlerProxy;
    /**
     * 取消关注公众号事件处理器
     */
    @Resource
    private UnSubscribeEventHandlerProxy unSubscribeEventHandlerProxy;
    /**
     * 用户未关注时扫描二维码事件处理器
     */
    @Resource
    private UnSubscribeScanEventHandlerProxy unSubscribeScanEventHandlerProxy;
    /**
     * 图片消息处理器代理
     */
    @Resource
    private ImageMessageHandlerProxy imageMessageHandlerProxy;
    /**
     * 链接消息处理器代理
     */
    @Resource
    private LinkMessageHandlerProxy linkMessageHandlerProxy;
    /**
     * 地理位置消息处理器代理
     */
    @Resource
    private LocationMessageHandlerProxy locationMessageHandlerProxy;
    /**
     * 小视频消息处理器代理
     */
    @Resource
    private ShortVideoMessageHandlerProxy shortVideoMessageHandlerProxy;
    /**
     * 文本消息处理器代理
     */
    @Resource
    private TextMessageHandlerProxy textMessageHandlerProxy;
    /**
     * 视频消息处理器代理
     */
    @Resource
    private VideoMessageHandlerProxy videoMessageHandlerProxy;
    /**
     * 语音消息处理器代理
     */
    @Resource
    private VoiceMessageHandlerProxy voiceMessageHandlerProxy;
    /**
     * 模板事件推送
     */
    @Resource
    private ModelMessagePushEventHandlerProxy modelMessagePushEventHandlerProxy;
    // endregion

    /**
     * 获取对应消息处理器的代理接口
     *
     * @return 代理接口数组
     */
    private BaseMessageHandlerProxy[] getBaseMessageHandlerProxies() {
        final BaseMessageHandlerProxy[] baseMessageHandlerProxies = new BaseMessageHandlerProxy[MAX_BASE_MESSAGE_HANDLER_PROXY];
        if (ArrayUtil.hasNull(baseMessageHandlerProxies)) {
            baseMessageHandlerProxies[0] = voiceMessageHandlerProxy;
            baseMessageHandlerProxies[1] = subscribeEventHandlerProxy;
            baseMessageHandlerProxies[2] = subscribeScanEventHandlerProxy;
            baseMessageHandlerProxies[3] = unSubscribeEventHandlerProxy;
            baseMessageHandlerProxies[4] = unSubscribeScanEventHandlerProxy;
            baseMessageHandlerProxies[5] = imageMessageHandlerProxy;
            baseMessageHandlerProxies[6] = linkMessageHandlerProxy;
            baseMessageHandlerProxies[7] = locationMessageHandlerProxy;
            baseMessageHandlerProxies[8] = shortVideoMessageHandlerProxy;
            baseMessageHandlerProxies[9] = textMessageHandlerProxy;
            baseMessageHandlerProxies[10] = videoMessageHandlerProxy;
            baseMessageHandlerProxies[11] = modelMessagePushEventHandlerProxy;
        }
        return baseMessageHandlerProxies;
    }

    /**
     * 验证消息来自微信服务器方法
     *
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     * @return 验证结果
     */
    @GetMapping("/")
    public String accessGet(@RequestParam String signature,
                            @RequestParam String timestamp,
                            @RequestParam String nonce,
                            @RequestParam String echostr) {
        LOGGER.info("参数[signature]={},[timestamp]={},[nonce]={}，[echostr]={}", signature, timestamp, nonce, echostr);
        boolean validate = validateWeChat.validate(signature, timestamp, nonce);
        if (validate) {
            LOGGER.info("验证成功...");
            return echostr;
        }
        LOGGER.info("验证失败...");
        return "wrong";
    }

    /**
     * 微信服务器推送过来的消息
     *
     * @param request 请求
     * @return 处理结果
     */
    @PostMapping("/")
    public String accessPost(HttpServletRequest request) {
        LOGGER.info("[进入处理微信请求的方法]");
        try {
            // 从微信请求中获取流
            ServletInputStream inputStream = request.getInputStream();
            // 创建一个xml解析对象
            SAXReader saxReader = new SAXReader();
            try {
                // 解析到一个Document中
                Document document = saxReader.read(inputStream);
                // 获取xml中根节点
                Element rootElement = document.getRootElement();
                // 解析成BaseMessage对象
                BaseMessageAndEventRequestAndResponse baseMessage = WxXmlAndObjectUtil.xmlToObject(rootElement, BaseMessageAndEventRequestAndResponse.class);
                LOGGER.info("[当前微信推送的类型是] {}", baseMessage.getMsgType());
                // 获取消息处理器代理
                BaseMessageHandlerProxy[] baseMessageHandlerProxies = getBaseMessageHandlerProxies();
                String handlerType = publicNumberProperties.getHandlerType().toUpperCase();
                AbstractMessageHandler messageHandler;
                switch (handlerType) {
                    case CUSTOMER_HANDLER:
                        LOGGER.info("[选择自定义的处理器]");
                        messageHandler = getCustomerHandler(ABSTRACT_MESSAGE_HANDLERS, baseMessageHandlerProxies);
                        break;
                    case ALL:
                        LOGGER.info("[选择默认的+自定义的处理器]");
                        messageHandler = getDefaultAndCustomerHandler(ABSTRACT_MESSAGE_HANDLERS, baseMessageHandlerProxies);
                        break;
                    case DEFAULT_HANDLER:
                    default:
                        LOGGER.info("[没有合适的处理器，选择默认的]");
                        messageHandler = getDefaultHandler(baseMessageHandlerProxies);
                        break;
                }
                BaseMessageResponse baseMessageResponse = messageHandler.handleMessage(baseMessage, rootElement);
                LOGGER.info("[返回信息] {}", baseMessageResponse);
                return WxXmlAndObjectUtil.objectToxml(baseMessageResponse);
            } catch (DocumentException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("[IOException] {}", e.getMessage());
        }
        return "success";
    }
}

package org.yyx.wx.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import static org.yyx.wx.websocket.WebSocketUtil.addUser;
import static org.yyx.wx.websocket.WebSocketUtil.sendMessageToUser;

/**
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/25-23:55
 */
@Component
@ServerEndpoint(value = "/push/{user}")
public class MessageWebSocket {

    /**
     * MessageWebSocket日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageWebSocket.class);
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        WebSocketUtil.removeUser(session);
    }

    /**
     * @param session 会话
     * @param error   异常
     */
    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.error("[错误]");
        error.printStackTrace();
        WebSocketUtil.removeUser(session);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(@PathParam("user") String user, String message) {
        try {
            sendMessageToUser(user, "服务器接收到的消息是: " + message);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("[消息发送失败]", user);
        }
    }

    //连接打开时执行
    @OnOpen
    public void onOpen(@PathParam("user") String user, Session session) {
        addUser(user, session);
        try {
            sendMessageToUser(user, "Connect WebSocketServer Success !! ");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("[消息发送失败]", user);
        }
    }
}

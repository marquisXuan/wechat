package org.yyx.wx.message.websocket;

import io.netty.util.internal.PlatformDependent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/8/26-00:17
 */
public class WebSocketUtil {

    /**
     * WebSocketUtil日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketUtil.class);

    /**
     * WebSocket用户
     */
    private static final ConcurrentMap<String, Session> USERS = PlatformDependent.newConcurrentHashMap();


    /**
     * 添加用户
     *
     * @param userName 用户名
     * @param session  会话
     */
    public static void addUser(String userName, Session session) {
        LOGGER.info("[用户{{}}加入连接]", userName);
        USERS.put(userName, session);
        printOnlineUsers();
    }


    /**
     * 输出在线信息
     */
    public static void printOnlineUsers() {
        LOGGER.info("[当前在线人数] {}人", USERS.size());
        Set<Map.Entry<String, Session>> entries = USERS.entrySet();
        for (Map.Entry<String, Session> entry : entries) {
            LOGGER.info("[用户名] {}", entry.getKey());
        }
    }

    /**
     * 移出用户
     *
     * @param session 会话信息
     */
    public static void removeUser(Session session) {
        String userName = null;
        Set<Map.Entry<String, Session>> entries = USERS.entrySet();
        for (Map.Entry<String, Session> entry : entries) {
            if (entry.getValue().equals(session)) {
                userName = entry.getKey();
                break;
            }
        }
        boolean remove = USERS.remove(userName, session);
        if (remove) {
            LOGGER.info("[用户{{}}退出成功]", userName);
        } else {
            LOGGER.info("[用户{{}}退出失败]", userName);
        }
    }

    /**
     * 给一个用户发送消息
     *
     * @param userName 用户名
     * @param message  消息内容
     * @throws IOException 异常
     */
    public static void sendMessageToUser(String userName, String message) throws IOException {
        LOGGER.info("[给用户{{}}发送消息] {}", userName, message);
        Session session = USERS.get(userName);
        if (session != null) {
            session.getBasicRemote().sendText(message);
        }
    }
}

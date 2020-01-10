
package com.devplatform.admin.webSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 交易
 * @author 杨崇兴
 *
 */
@ServerEndpoint(value = "/websocket/commodity/{fromUserId}/{toUserId}")
@Component
public class WebSocketServer {
    
    protected Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    
    // 已经建立链接的对象缓存起来
    private static ConcurrentMap<String, WebSocketServer> serverMap = new ConcurrentHashMap<String, WebSocketServer>();
    
    //线程安全的静态变量，表示在线连接数
    private static volatile int onlineCount = 0;
    
    // 当前session
    private Session currentSession;
    
    /**
     * 消息订阅
     * Description:
     * Company:
     * @author suochaochao
     * 2018年9月13日下午4:35:16
     * @param session
     * @param fromUserId
     * @param toUserId
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("fromUserId") String fromUserId, @PathParam("toUserId") String toUserId) throws IOException {
        
        WebSocketServer server = serverMap.get(fromUserId);//若存在则用户在线，否在用户不在线
        logger.info("订阅用户ID=" + fromUserId);
        if (server != null && server.currentSession.isOpen()) {
            logger.info("该用户已在线，userId=" + fromUserId);
        }
        else {
            this.currentSession = session;
            serverMap.put(fromUserId, this);//建立链接时，缓存对象
            addOnlineCount(); //在线数加1
            System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        }
    }
    
    /**
     * 关闭消息
     * Description:
     * Company:
     * @author suochaochao
     * 2018年9月13日下午4:35:32
     * @param session
     * @param reason
     */
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println(reason.toString());
        if (serverMap.containsValue(this)) {
            Iterator<String> keys = serverMap.keySet().iterator();
            String userId = "0";
            while (keys.hasNext()) {
                userId = keys.next();
                if (serverMap.get(userId) == this) {
                    serverMap.remove(userId, this);//关闭链接时，删除缓存对象
                    logger.info("关闭链接时，删除缓存对象，userId=" + userId);
                    subOnlineCount(); //在线数减一
                }
            }
        }
        this.currentSession = null;
        try {
            session.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 发送消息
     * Description:
     * Company:
     * @author suochaochao
     * 2018年9月13日下午4:35:50
     * @param json 消息内容
     * @param type 1新增消息，2消除消息
     */
    @OnMessage()
    @SuppressWarnings("unchecked")
    public void onMessage(String json) throws IOException {
        
        logger.info("来自客户端的消息:" + json);
        
        HashMap<String, String> map = JSON.parseObject(json, HashMap.class);
        String fromUserId = map.get("fromUserId").toString();
        String toUserId = map.get("toUserId").toString();
        String content = map.get("content").toString();
        WebSocketServer server = serverMap.get(toUserId);//若存在则用户在线，否在用户不在线
        
        if (server != null && server.currentSession.isOpen()) {
            if (fromUserId != toUserId) {
                server.currentSession.getAsyncRemote().sendText(content);
            }
        }
    }
    
    /**
     * 消息失败
     * Description:
     * Company:
     * @author suochaochao
     * 2018年9月13日下午4:36:01
     * @param t
     */
    @OnError
    public void onError(Throwable t) {
        logger.info("webSocket发送消息，发生错误。");
        t.printStackTrace();
    }
    
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
    
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
    
}

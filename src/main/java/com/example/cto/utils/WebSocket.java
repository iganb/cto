package com.example.cto.utils;

import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author 张驰
 * @Date 2022/12/12 10:12
 * @Version 1.0
 */
@ServerEndpoint("/bindingRecord")
@Component
public class WebSocket {
    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    /**
     * 新建webSocket配置类
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    /**
     * 建立连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);

    }

    /**
     * 断开连接
     */
    @OnClose
    public void onClose(){
        webSockets.remove(this);
    }

    /**
     * 接收到信息
     * @param message
     */
    @OnMessage
    public void onMessage(String message){

    }

    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(String message){

        for (WebSocket webSocket : webSockets) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {

            }
        }
    }

}

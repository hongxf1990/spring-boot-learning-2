package com.petter.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 虽然@Component默认是单例模式的，
 * 但springboot还是会为每个websocket连接 初始化一个bean，所以可以用一个静态set保存起来。
 * @author hongxf
 * @since 2019-02-13 14:20
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {

    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private String nickName;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("nickname") String nickname) {
        this.session = session;
        this.nickName = nickname;

        webSocketSet.add(this);     //加入set中
        System.out.println("有新人来了，它是" + nickname +
                "！当前在线人数为" + webSocketSet.size());
        this.session.getAsyncRemote().sendText("恭喜您成功连接上WebSocket-->当前在线人数为："+webSocketSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自" + nickName +
                "的消息:" + message);

        //群发消息
        broadcast(message);
    }

    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发自定义消息
     * */
    private void broadcast(String message){
        for (MyWebSocket item : webSocketSet) {
            //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
            //this.session.getBasicRemote().sendText(message);
            item.session.getAsyncRemote().sendText(nickName + "：" + message);//异步发送消息.
        }
    }
}

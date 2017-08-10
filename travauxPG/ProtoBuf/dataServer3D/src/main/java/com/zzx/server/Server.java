package com.zzx.server;

import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created by wenkai on 8/9/17.
 */
public class Server {
    public static void main(String[] args) throws Exception{
        //服务端在20006端口监听客户端请求的TCP连接
        ServerSocket server = new ServerSocket(20006);
        System.out.println("socket server for 3D standby, 20006 as port");
        Socket client = null;
        boolean f = true;
        int threadNum = 0;
        while(f){
            //等待客户端的连接，如果没有获取连接
            client = server.accept();
            System.out.println("A new client connects succeed！");
            //为每个客户端连接开启一个线程
            new Thread(new ServerThread(client, threadNum)).start();
            threadNum++;
        }
        server.close();
    }
}

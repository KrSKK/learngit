package com.example.server;

import java.net.ServerSocket;  
import java.net.Socket;  

public class Server1 {  
    public static void main(String[] args) throws Exception{  
		int threadNum = 0;
        //服务端在20006端口监听客户端请求的TCP连接  
        ServerSocket server = new ServerSocket(20006);  
		System.out.println("socket服务已启动,端口为20006");
        Socket client = null;  
        boolean f = true;  
        while(f){  
            //等待客户端的连接，如果没有获取连接  
            client = server.accept();  
            System.out.println("客户端"+threadNum+" 连接成功！");  
            //为每个客户端连接开启一个线程  
            new Thread(new ServerThread(client, threadNum)).start();  
			threadNum++;
        }  
        server.close();  
    }  
}

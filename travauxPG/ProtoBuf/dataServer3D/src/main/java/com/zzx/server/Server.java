package com.zzx.server;

import com.zzx.dbHandlers.BaseDao;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

/**
 * Created by wenkai on 8/9/17.
 * 服务器主程序
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
            //test field
            /*int a = 257;
            byte[] b = new byte[4];
            for(int i = 0; i < 4; i ++){
                b[i] = (byte)((a >> 8*(3-i)) & 0xFF);
                System.out.println(b[i]);
            }
            System.out.println(b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24);*/
            //System.out.println();
            //System.out.println(Integer.parseInt("000"));
            //System.out.println("55".getBytes().length);


            //等待客户端的连接，如果没有获取连接,停留于此
            client = server.accept();
            System.out.println("*******************************************");
            System.out.println("A new client connects succeed！");
            //为每个客户端连接开启一个线程
            BaseDao baseDao = new BaseDao();
            Connection conn = baseDao.DbConnector();
            new Thread(new ReceiveThread(client, baseDao, conn, threadNum)).start();
            //new Thread(new SendThread(client, threadNum, "00", baseDao)).start();
            threadNum++;
        }
        server.close();
    }
}

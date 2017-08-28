package com.zzx.server;

import com.zzx.dbHandlers.BaseDao;
import com.zzx.protoClasses.DbDataProto.DbData;
import com.zzx.protoClasses.DbDataProto.DbDataList;
import com.zzx.protoHandlers.MsgHandlers;
import com.zzx.protoHandlers.PersonHandler;
import com.zzx.protoClasses.AddressBookProtos.AddressBook;
import com.zzx.protoClasses.AddressBookProtos.Person;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.String;
import java.sql.Connection;

import com.zzx.protoClasses.DataProto.Data;

/**
 * Created by wenkai on 8/9/17.
 * 服务器数据接收线程
 */
public class ReceiveThread implements Runnable{
    private Socket client = null;
    private BaseDao baseDao;
    private Connection connection;
    private int threadId;
    private int byteLength = 1024;
    private String type;                //系统标记及数据库操作标记

    public ReceiveThread(Socket client, BaseDao baseDao, Connection connection, int id){
        this.client = client;
        this.threadId = id;
        this.baseDao = baseDao;
        this.connection = connection;
    }

    //@Override
    public void run() {
        try{
            //获取Socket的输入流,接收数据
            InputStream input = client.getInputStream();
            //获取Socket的输出流,发送数据
            OutputStream output = client.getOutputStream();
            boolean flag = true;
            boolean sendflag = true;
            PersonHandler ph = new PersonHandler();
            MsgHandlers mh = new MsgHandlers();
            byte[] arry = new byte[byteLength];
            int length = 0;
            int tag = 1;

            //接受client请求,初始化场景系统
            while (sendflag){
                try {
                    if((length = input.read(arry)) > 0){
                        type = mh.typeParser(arry);
                        new Thread(new SendThread(client, threadId, type, baseDao));
                        sendflag = false;
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            }

            //接收场景数据更改
            while(flag){
                try{
                    client.sendUrgentData(0xFF);
					//Thread.sleep(1000);
                }catch(Exception e){
                    System.out.println(e);
                    flag = false;
                }
                //接收从客户端发送过来的数据
                try{
                    //length = input.read(arry);
                    //System.out.println(length);
                    if((length = input.read(arry)) > 0){
                        //System.out.println(length);
                        byte[] datain = new byte[length];
                        for(int i = 0; i < length; i++){
                            //System.out.println(arry[i]);
                            datain[i] = arry[i];
                        }
                        String exec = mh.typeParser(arry);

                        DbDataList dbDataList = DbDataList.parseFrom(datain);
                        for (DbData dbData: dbDataList.getDataListList()){
                            String sql = baseDao.SQLMaker(exec, type, dbData);
                            baseDao.DataUpdate(sql);
                        }

                        System.out.println("*******************************************");
                        System.out.println("client"+threadId+"\'s message recevied");
                        System.out.println("*******************************************");
                    }
                }catch (SocketTimeoutException ste){
                    System.out.println("Time out, no response!");
                }catch(Exception e){
                    System.out.println(e);
                }finally {
                    tag++;
                }
            }
            input.close();
            output.close();
            client.close();
            connection.close();
            System.out.println("client disconnects!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

package com.zzx.server;

import com.zzx.dbHandlers.BaseDao;
import com.zzx.protoClasses.DbDataProto.DbData;
import com.zzx.protoClasses.DbDataProto.DbDataList;
import com.zzx.protoHandlers.PersonHandler;
import com.zzx.protoClasses.AddressBookProtos.AddressBook;
import com.zzx.protoClasses.AddressBookProtos.Person;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.lang.String;
import java.sql.ResultSet;

import com.zzx.protoClasses.DataProto.Data;
import com.zzx.protoHandlers.MsgHandlers;

/**
 * Created by wenkai on 8/9/17.
 * 服务器数据传输线程
 */
public class SendThread implements Runnable{
    private Socket client = null;
    private int threadId;
    private String type;
    private BaseDao baseDao;

    public SendThread(Socket client, int id, String type, BaseDao baseDao){
        this.client = client;
        this.threadId = id;
        this.type = type;
        this.baseDao = baseDao;
    }


    //@Override
    public void run() {
        try{
            //获取Socket输入流,接收数据
            //InputStream input = client.getInputStream();
            //获取Socket输出流,发送数据
            OutputStream output = client.getOutputStream();
            boolean flag =true;
            PersonHandler ph = new PersonHandler();
            MsgHandlers mh = new MsgHandlers();
            int tag = threadId;


            //初始化场景物体数据
            DbDataList.Builder listBuilder = DbDataList.newBuilder();
            ResultSet searchResult = baseDao.DataResearch(type);
            while (searchResult.next()){
                DbData.Builder DbBiulder = DbData.newBuilder();
                DbBiulder.setGuiId(searchResult.getInt("guid"));
                DbBiulder.setDeviceId(searchResult.getInt("deviceid"));
                DbBiulder.setDeviceName(searchResult.getString("devicename"));
                DbBiulder.setDevicePos(searchResult.getString("devicepos"));
                DbBiulder.setDeviceIp(searchResult.getString("deviceip"));
                DbBiulder.setModule(searchResult.getString("module"));
                listBuilder.addDataList(DbBiulder);
            }
            searchResult.close();
            byte[] dataList = listBuilder.build().toByteArray();
            byte[] dataSender = mh.senderBuilder(dataList, "10".getBytes());
            output.write(dataSender);
            output.flush();

            //发送相关专业实时数据以及报警信息
            while(flag){
                //心跳包
				try{
					client.sendUrgentData(0xFF);
                    //Thread.sleep(1000);
				}catch(Exception e){
					System.out.println(e);
					flag = false;
				}

                //向客户端发送相关专业实时数据
                try{
                    switch (Integer.parseInt(type)){
                        case 50:

                    }
                    byte[] dataout = ph.MsgBuilder(tag);
                    byte[] send = mh.senderBuilder(dataout, type.getBytes());
                    output.write(send);
                    output.flush();
                    System.out.println("message " + tag + " sent! The length is: " + send.length);
                    //Thread.sleep(500);
                }catch(Exception e){
                    System.out.println(e);
                }finally {
                    tag++;
                }
            }
            //input.close();
            output.close();
            client.close();
            System.out.println("client disconnects!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

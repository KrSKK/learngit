package com.zzx.server;

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

/**
 * Created by wenkai on 8/9/17.
 */
public class ServerThread implements Runnable{
    private Socket client = null;
    private int threadId;
    public ServerThread(Socket client, int id){
        this.client = client;
        this.threadId = id;
    }

    //@Override
    public void run() {
        try{
            //获取Socket的输出流，用来向客户端发送数据
            PrintStream outp = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader inbuf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            InputStream input = client.getInputStream();
            OutputStream output = client.getOutputStream();
            boolean flag =true;
            PersonHandler ph = new PersonHandler();
            byte[] arry = new byte[1024];
            int length = 0;
            int tag = 1;
            while(flag){
                //心跳包
				/*try{
					client.sendUrgentData(0xFF);
					System.out.println("connentor holding...");
					System.out.println("******************************************");
					//Thread.sleep(1000);
					TimeUnit.SECONDS.sleep(1);
				}catch(SocketException e){
					System.out.println(e);
					System.out.println("client "+threadId+"disconnects");
					flag = false;
				}*/
                //接收从客户端发送过来的数据
                try{
                    byte[] send = ph.MsgBuilder(tag);
                    output.write(send);
                    output.flush();
                    System.out.println("message " + tag + " sent!");
                    if((length = input.read(arry)) != -1){
                        //System.out.println(length);
                        byte[] data = new byte[length];
                        for(int i = 0; i < length; i++){
                            //System.out.println(arry[i]);
                            data[i] = arry[i];
                        }
                        AddressBook addressBook = AddressBook.parseFrom(data);
                        System.out.println("client"+threadId+"\'s message recevied");
                        ph.MsgParser(addressBook);
                        System.out.println("*******************************************");
                    }else{
                        System.out.println("continue");
                        System.out.println("*******************************************");
                        continue;
                    }
                    Thread.sleep(5000);
                }catch (SocketTimeoutException ste){
                    System.out.println("Time out, no response!");
                }catch(Exception e){
                    System.out.println(e);
                }finally {
                    tag++;
                }
            }
            input.close();
            outp.close();
            client.close();
            System.out.println("client disconnectes!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

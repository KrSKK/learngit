package com.example.server;

import com.example.protoHandlers.PersonHandler;
import com.example.protoclasses.AddressBookProtos.AddressBook;
import com.example.protoclasses.AddressBookProtos.Person;
import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.io.PrintStream;  
import java.net.Socket;  
import java.net.SocketException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/** 
 * 该类为多线程类，用于服务端 
 */  
public class ServerThread implements Runnable {  

    private Socket client = null;  
	private int threadId;
    public ServerThread(Socket client, int id){  
        this.client = client;  
		this.threadId = id;
    }  

    @Override  
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
			int tag = 11;
            while(flag){  
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
					byte[] send = ph.addressMaker(tag);
					output.write(send);
					output.flush();
				}catch(Exception e){
					System.out.println(e);
				}
				tag++;
				if((length = input.read(arry)) != -1){
					//System.out.println(length);
					byte[] data = new byte[length];
					for(int i = 0; i < length; i++){
						//System.out.println(arry[i]);
						data[i] = arry[i];
					}
					AddressBook addressBook = AddressBook.parseFrom(data);
					System.out.println("client"+threadId+"\'s message recevied");
					ph.Print(addressBook);
					System.out.println("*******************************************");
				}else{
					System.out.println("continue");
					System.out.println("*******************************************");
					continue;
				}
                /*String str =  buf.readLine();  
				System.out.println(str);
				if(str != null && !"".equals(str)){
					byte[] res = str.getBytes();
					System.out.println(res);
					AddressBook addressBook = AddressBook.parseFrom(res);
					ph.Print(addressBook);
				}*/
                /*if(str == null || "".equals(str)){  
					//out.println("test\n");
					//Thread.sleep(2000);
					//System.out.println("no request\n");
                    flag = false;  
                }else{  
                    if("bye".equals(str)){  
                        flag = false;  
						System.out.println("客户端连接断开");
                    }else{  
                        //将接收到的字符串前面加上echo，发送到对应的客户端  
                        //out.println("echo:" + str);  
						byte[] res = str.getBytes();
						System.out.println(res);
						AddressBook addressBook = AddressBook.parseFrom(res);
						ph.Print(addressBook);
                    }  
                }*/
				//out.println("let's go!\n");  
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

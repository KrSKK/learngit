package com.example.client;  

import com.example.protoHandlers.PersonHandler;
import com.example.protoclasses.AddressBookProtos.AddressBook;
import com.example.protoclasses.AddressBookProtos.Person;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintStream;  
import java.net.Socket;  
import java.net.SocketTimeoutException;  
import java.io.OutputStream;
import java.io.InputStream;

public class Client1 {  
    public static void main(String[] args) throws IOException {  
        //客户端请求与本机在20006端口建立TCP连接   
        Socket client = new Socket("127.0.0.1", 20006);  
        client.setSoTimeout(10000);  
		System.out.println("服务器连接成功");
        //获取键盘输入   
        BufferedReader inputbuf = new BufferedReader(new InputStreamReader(System.in));  
        //获取Socket的输出流，用来发送数据到服务端    
        PrintStream outp = new PrintStream(client.getOutputStream());  
		OutputStream output = client.getOutputStream();
		InputStream input = client.getInputStream();
        //获取Socket的输入流，用来接收从服务端发送过来的数据    
        BufferedReader inbuf =  new BufferedReader(new InputStreamReader(client.getInputStream()));  
        boolean flag = true;  
		PersonHandler ph = new PersonHandler();
		int tag = 1;
		int length = 0;
		byte[] arry = new byte[1024];
        while(flag){  
			try{
				//byte[] add = ph.addressMaker();
				byte[] add = ph.addressMaker(tag);
				//System.out.println(add.length);
				/*AddressBook ad = AddressBook.parseFrom(add);
				ph.Print(ad);
				System.out.println("*********************************************");*/
				output.write(add);
				output.flush();
				Thread.sleep(5000);
			}catch(SocketTimeoutException e){
				System.out.println("Time out, No response!");
			}catch(Exception e){
				System.out.println(e);
			}
			tag++;
			/*try{
				Thread.sleep(5000);
			}catch(Exception e){
				System.out.println(e);
			}*/
			if((length = input.read(arry)) != -1){                                                                                                                                      
                //System.out.println(length);
                byte[] data = new byte[length];
                for(int i = 0; i < length; i++){
                    //System.out.println(arry[i]);
                    data[i] = arry[i];
                }
                AddressBook addressBook = AddressBook.parseFrom(data);
                System.out.println("server\'s message recevied");
                ph.Print(addressBook);
                System.out.println("*******************************************");
            }    
			/*try{
				client.sendUrgentData(0xff);
				System.out.println("connect remains...");
				Thread.sleep(5000);
			}catch(Exception e){
				try{
					int reconTimes = 0;
					while(true){
						client = new Socket("127.0.0.1", 20006);
						if(client.isConnected()){
							client.setSoTimeout(10000);
							System.out.println("reconnect server succeed");
							break;
						}
						reconTimes++;
						if(reconTimes >= 9){
							break;
						}
					}
				}catch(Exception se){
					System.out.println("server is crashed!!!");
				}
			}*/
			//String dataEnter = new String(add);
			//System.out.println(add);
			//out.println(dataEnter);
			//AddressBook ad = AddressBook.parseFrom(add);
			//ph.Print(ad);
            /*System.out.print("输入信息：");  
            String str = input.readLine();  
            //发送数据到服务端    
            out.println(str);  
            if("bye".equals(str)){  
                flag = false;  
            }else{  
                try{  
                    //从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常  
                    String echo = buf.readLine();  
                    System.out.println(echo);  
                }catch(SocketTimeoutException e){  
                    System.out.println("Time out, No response");  
                }  
            }*/
			/*try{
				String echo = buf.readLine();
				System.out.println(echo);
			}catch(SocketTimeoutException e){
				System.out.println("Time out, No response");
			}*/  
        }  
        inputbuf.close();  
        if(client != null){  
            //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭  
            client.close(); //只关闭socket，其关联的输入输出流也会被关闭  
        }  
    }  
}

import java.io.*;
import java.net.*;
class Server {
  
  private static Socket client;
  private static DataOutputStream dos;
  private static DataInputStream dis;
  
  public static boolean sendMsg(String flag){
     
   try {
     dos=new DataOutputStream(client.getOutputStream());
     dos.write(flag.getBytes("UTF-8")); //发送的也是字节流，C#那端接收的也是字节流
     dos.flush();
   } catch (IOException e) {
   }
   return true;
   }

  public static String getMsg()
  {
     byte[]  b=new byte[1024];
     String message="";
   try {
    
     dis =new  DataInputStream(client.getInputStream());
     dis.read(b,0, b.length);
     message=new String(b,"UTF-8"); //关键！接收的是字节流，要转换为字符串才显示
   } catch (IOException e) {
     e.printStackTrace();
   }
   return message.trim(); //去掉空格
   }
  
  public static void main(String[] args) throws IOException{
     ServerSocket server = new ServerSocket(8895);
     while(true)
     {
       client = server.accept();
       sendMsg("已经和服务器连接上。");
       dos.flush();
       while(true)
       {
         String str = getMsg();
         System.out.println(str.trim());
         if(str.trim().equals("end"))     
         {
           sendMsg("准备服务器断开连接。");
           dos.flush();
           break;
         }
         else
         {
           sendMsg("has receive....");
           dos.flush();
         }
       }
       client.close();
     }
  }
}

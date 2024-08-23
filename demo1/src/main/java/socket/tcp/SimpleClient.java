package socket.tcp;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/23 21:07
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public  class  SimpleClient  {
    public  static  void  main(String[]  args)  {
        String  serverAddress  =  "localhost";  //  服务器地址（本地运行时可以用localhost）
        int  port  =  12345;  //  服务器端口

        try  (Socket socket  =  new  Socket(serverAddress,  port);
              PrintWriter  out  =  new  PrintWriter(socket.getOutputStream(),  true);
              BufferedReader  in  =  new  BufferedReader(new  InputStreamReader(socket.getInputStream()));
              BufferedReader  stdIn  =  new  BufferedReader(new  InputStreamReader(System.in)))  {

            String  userInput;

            System.out.println("请输入消息到服务器（按CTRL+C退出）：");
            //  从标准输入读取用户输入并发送到服务器
            while  ((userInput  =  stdIn.readLine())  !=  null)  {
                out.println(userInput);  //  发送消息
                System.out.println("服务器回复:  "  +  in.readLine());  //  接收回复
            }
        }  catch  (IOException  e)  {
            e.printStackTrace();
        }
    }
}
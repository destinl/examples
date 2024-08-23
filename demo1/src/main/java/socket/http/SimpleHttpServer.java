package socket.http;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/23 21:12
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    public static void main(String[] args) {
        int port = 8080; // 服务器运行的端口
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("HTTP 服务器正在运行，监听端口 " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // 等待客户端连接
                System.out.println("客户端已连接：" + clientSocket.getInetAddress());
                        
                new Thread(() -> handleRequest(clientSocket)).start(); // 新线程处理请求
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // 读取请求头
            String inputLine = in.readLine();
            if (inputLine != null && inputLine.startsWith("GET")) {
                System.out.println("请求行: " + inputLine);
                // 解析请求的资源
                String[] requestParts = inputLine.split(" ");
                String path = requestParts[1];

                // 根据请求路径生成响应内容
                String responseBody = "<html><body><h1>Hello, HTTP!</h1><p>You requested: " + path + "</p></body></html>";
                        
                // 设置响应头
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/html; charset=UTF-8");
                out.println("Content-Length: " + responseBody.length());
                out.println(); // 空行，表示头部结束
                out.println(responseBody); // 输出响应体
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close(); // 关闭与客户端的连接
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package exp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String HOST = "127.0.0.1";  // 服务器地址
    private static final int PORT = 8000;  // 服务器端口号

    public static void main(String[] args) {
        try {
            // 连接服务器
            Socket socket = new Socket(HOST, PORT);

            // 获取输入输出流
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 发送请求
            out.println("Time");

            // 接收响应并输出
            String response = in.readLine();
            System.out.println("Current Time: " + response);

            // 发送结束请求
            out.println("Bye");

            // 关闭连接
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

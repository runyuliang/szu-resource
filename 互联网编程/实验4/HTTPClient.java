package exp4;
import java.io.*;
import java.net.Socket;

public class HTTPClient {
    public static void main(String[] args) {
        String serverHost = "localhost";
        int serverPort = 8080;

        try (Socket socket = new Socket(serverHost, serverPort)) {
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            // 发送GET请求
            writer.println("GET D:/index.html HTTP/1.1");
            writer.println("Host: " + serverHost);
            writer.println();

            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // 读取响应
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 发送HEAD请求
            writer.println("HEAD D:/index.html HTTP/1.1");
            writer.println("Host: " + serverHost);
            writer.println();

            // 读取响应
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 发送POST请求
            String postData = "name=John&age=25";
            writer.println("POST /submit HTTP/1.1");
            writer.println("Host: " + serverHost);
            writer.println("Content-Type: application/x-www-form-urlencoded");
            writer.println("Content-Length: " + postData.length());
            writer.println();
            writer.println(postData);

            // 读取响应
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

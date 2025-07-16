package exp4;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HTTPServer {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // 处理客户端请求的新线程
                Thread thread = new Thread(() -> handleClientRequest(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream()
        ) {
            // 读取请求行
            String requestLine = in.readLine();
            System.out.println("Request: " + requestLine);

            // 解析请求方法和路径
            String[] requestParts = requestLine.split(" ");
            String method = requestParts[0];
            String path = requestParts[1];

            // 处理GET请求
            if (method.equals("GET")) {
                // 判断请求路径是否为静态网站的文件
                if (isStaticFile(path)) {
                    // 读取文件内容
                    byte[] content = readFileContent(path);

                    // 构造响应
                    sendResponse(out, "200 OK", "text/html", content);
                } else {
                    // 处理文件不存在的情况
                    sendResponse(out, "404 Not Found", "text/plain", "File not found".getBytes());
                }
            }

            // 处理HEAD请求
            if (method.equals("HEAD")) {
                // 判断请求路径是否为静态网站的文件
                if (isStaticFile(path)) {
                    // 构造响应头
                    sendResponseHeader(out, "200 OK", "text/html");
                } else {
                    // 处理文件不存在的情况
                    sendResponseHeader(out, "404 Not Found", "text/plain");
                }
            }

            // 处理POST请求
            if (method.equals("POST")) {
                // 根据路径执行相应的业务逻辑
                if (path.equals("/submit")) {
                    // 处理表单提交等操作
                    // ...

                    // 构造响应
                    sendResponse(out, "200 OK", "text/plain", "Post request handled".getBytes());
                } else {
                    // 处理路径不匹配的情况
                    sendResponse(out, "404 Not Found", "text/plain", "Path not found".getBytes());
                }
            }

            // 关闭客户端连接
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isStaticFile(String path) {
        // 在这里根据实际情况判断路径是否为静态网站的文件
        // 返回true表示是静态文件，可以根据扩展名判断，例如：path.endsWith(".html")
        // 返回false表示不是静态文件，例如：path.equals("/api")
        return path.endsWith(".html");
    }

    private static byte[] readFileContent(String path) throws IOException {
        Path filePath = Paths.get(path);
        return Files.readAllBytes(filePath);
    }

    private static void sendResponse(OutputStream out, String status, String contentType, byte[] content) throws IOException {
        // 构造响应头
        sendResponseHeader(out, status, contentType);

        // 发送响应实体
        out.write(content);
        out.flush();
    }

    private static void sendResponseHeader(OutputStream out, String status, String contentType) throws IOException {
        // 构造响应头
        String responseHeader = "HTTP/1.1 " + status + "\r\n";
        responseHeader += "Content-Type: " + contentType + "\r\n";
        responseHeader += "Connection: close\r\n";
        responseHeader += "\r\n";

        // 发送响应头
        out.write(responseHeader.getBytes());
        out.flush();
    }
}


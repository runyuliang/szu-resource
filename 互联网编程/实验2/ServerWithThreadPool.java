package exp2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerWithThreadPool {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started. Listening to the port 8000...");

            // 创建线程池
            ExecutorService threadPool = Executors.newCachedThreadPool();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("A new client has connected.");
                String clientIP = clientSocket.getInetAddress().getHostAddress();
                Logger logger = new Logger();
                logger.log("Connection from " + clientIP + " at " + new Date());
                // 将客户端连接交给线程池处理
                threadPool.execute(() -> {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);


                        String request;
                        while ((request = in.readLine()) != null) {
                            System.out.println("Received request: " + request);

                            if (request.equals("Time")) {
                                Date now = new Date();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                                String response = sdf.format(now);
                                out.println(response);
                            } else if (request.equals("Bye")) {
                                break;
                            } else {
                                out.println("Invalid request.");
                            }
                        }

                        // 关闭输入输出流和客户端连接
                        in.close();
                        out.close();
                        clientSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


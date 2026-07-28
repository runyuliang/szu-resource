package UDP;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient2 {
    public static void main(String[] args) throws Exception {
        // 定义服务器的地址和端口号
        InetAddress address = InetAddress.getLocalHost();
        int ServerPort = 2222;

        // 创建发送端对象，端口号在代码中固定
        DatagramSocket socket = new DatagramSocket(3333);

        // 交互提示信息
        System.out.println("The client started successfully.");
        System.out.println("Messages from " + address + " on port 2222");

        // 开2个线程不停地向服务端发送信息和接收数据
        new Thread(new MessageSend(socket, address, ServerPort)).start();
        new Thread(new MessageReceive(socket)).start();
    }
}

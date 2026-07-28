package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MessageSend implements Runnable {
    DatagramSocket socket; //套接字
    InetAddress address; //IP地址
    int ServerPort;//服务器IP

    MessageSend(DatagramSocket socket, InetAddress address, int ServerPort) {
        this.address = address;
        this.ServerPort = ServerPort;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Enter your message");
                Scanner sc = new Scanner(System.in);//从键盘接受数据
                String msg = sc.nextLine();
                byte[] buffer = msg.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, ServerPort);
                //创建一个数据包对象封装数据
                socket.send(packet);//发送数据
                System.out.println("Your message has been sent.");//提示信息
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

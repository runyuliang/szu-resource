package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MessageReceive implements Runnable {
    DatagramSocket socket;

    MessageReceive(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] buf = new byte[1024 * 64];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);//创建一个数据包对象接收数据
                socket.receive(packet);//接收服务器响应的数据
                int len = packet.getLength();//以下开始取出packet里的数据
                String rs = new String(buf, 0, len);
                System.out.println("Received message from " + packet.getAddress() + " on port " + packet.getPort() + ": " + rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
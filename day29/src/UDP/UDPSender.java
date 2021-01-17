package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 发送端
 */
public class UDPSender {
    public static void main(String[] args) throws IOException {
//        1、创建发送端对象
        DatagramSocket ds = new DatagramSocket(12345);
//        2、创建数据报对象
        byte[] bs = "服务端你好啊！".getBytes();
        DatagramPacket dp = new DatagramPacket(bs, bs.length, InetAddress.getByName("127.0.0.1"), 54321);
//        3、使用发送端发送数据报
        ds.send(dp);
//        4、使用发送端接收数据报
        ds.receive(dp);
        System.out.println("接收到数据:" + new String(dp.getData(), 0, dp.getLength()));
//        5、释放资源
        ds.close();
    }
}

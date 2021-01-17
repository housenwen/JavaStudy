package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 接收端
 */
public class UDPReceiver {
    public static void main(String[] args) throws IOException {
//        1、创建接收端对象
        DatagramSocket ds = new DatagramSocket(54321);
//        2、创建数据报对象
        DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
//        3、使用接收端接收数据
        ds.receive(dp); //receive方法具有阻塞功能
        //打印
        int len = dp.getLength();
        byte[] bs = dp.getData();
        System.out.println("接收数据:" + new String(bs, 0, len));
//        4、使用接收端发送数据报
        byte[] bytes = "你好发送端".getBytes();
        ds.send(new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"),12345));
//        5、释放资源
        ds.close();

    }
}

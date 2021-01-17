package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //1.创建服务器对象
        ServerSocket server = new ServerSocket(6666);
        while (true) {
            System.out.println("服务器启动成功...等待客户端连接...");
            //2.接收客户端
            Socket client = server.accept(); //accept方法具有阻塞功能
            //3.获取通道中的输入流
            InputStream in = client.getInputStream();
            //4.调用in的read方法
//        byte[] bs = new byte[1024];
//        int len = in.read(bs);//网络流中read方法也具有阻塞功能
//        System.out.println("客户端发来贺电:" + new String(bs, 0, len));

            int len;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes)) != -1) {
                System.out.println("客户端发来贺电:" + new String(bytes, 0, len));
            }

            //5.释放资源
            in.close();
            client.close();
//            server.close();// 服务器可以不用关闭，需要接受数据
            System.out.println("服务器关闭...");

        }
    }
}
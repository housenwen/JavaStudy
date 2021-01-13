package ServerSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class test3 {
    public static void main(String[] args) throws IOException {
        //ServerSocket 服务端
        //1.创建服务端对象，绑定端口
        ServerSocket ss = new ServerSocket(8888);
        while (true){
            //2.调用accept方法等待客户端的连接
            System.out.println("等待客户端连接！");
            Socket socket = ss.accept();
            System.out.println("客户端已连接:"+socket);
        }
    }
}

package ServerSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class test {
    public static void main(String[] args) throws IOException {
        ServerSocket  ss = new ServerSocket(8888);
        while (true){
            System.out.println("等待客户端连接");
            Socket socket = ss.accept();//阻塞，等待客户端连接
            System.out.println("客户端已连接"+socket);
        }
    }
}

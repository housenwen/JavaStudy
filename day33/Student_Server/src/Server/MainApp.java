package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainApp {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8888)) {
            while (true) {
                System.out.println("等待客户端连接...");
                Socket socket = server.accept(); //开启线程
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

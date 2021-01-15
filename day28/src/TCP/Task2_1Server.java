package TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task2_1Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        ExecutorService pool = Executors.newFixedThreadPool(10);

        while (true){
            System.out.println("等待客户端连接");
            Socket socket = serverSocket.accept();
            pool.submit(new Task3_1Web(socket));
        }

    }
}

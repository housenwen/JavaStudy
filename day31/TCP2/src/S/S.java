package S;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class S {
    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true){
            System.out.println("等待浏览器链接：");
            Socket socket = serverSocket.accept();
            threadPool.submit(new Task(socket));
        }
    }
}

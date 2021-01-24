package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class s {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((in.read(bytes))!=-1){
            System.out.println(new String(bytes));
        }
        in.close();
//        serverSocket.close();
    }
}


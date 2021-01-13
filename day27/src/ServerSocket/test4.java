package ServerSocket;

import java.io.IOException;
import java.net.Socket;

public class test4 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        System.out.println(socket);
    }
}

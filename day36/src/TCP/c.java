package TCP;

import java.io.*;
import java.net.Socket;

public class c {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        OutputStream out = socket.getOutputStream();
        out.write("你好我是客户端小白".getBytes());
        out.close();
        socket.close();
    }
}

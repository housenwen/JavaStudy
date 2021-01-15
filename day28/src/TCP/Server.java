package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("等待客户端连接");
        Socket socket  = server.accept();
        System.out.println(socket);
        InputStream in = socket.getInputStream();
        byte[] buf = new byte[1024];
        int len = in.read(buf);
        System.out.println("len="+len);
        System.out.println("客户端发送的数据是："+new String(buf,0,len));

        socket.close();

    }
}

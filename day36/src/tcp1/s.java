package tcp1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class s {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        FileOutputStream fos = new FileOutputStream("123.png");
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((in.read(bytes))!=-1){
            fos.write(bytes,0,len);
        }

        in.close();
        out.close();
        fos.close();

    }
}

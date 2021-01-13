package ServerSocket;

import java.io.*;
import java.net.Socket;

public class test1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        OutputStream os = socket.getOutputStream();
        os.write("在吗？我是好人！".getBytes());
        socket.shutdownOutput();

        InputStream  is = socket.getInputStream();

        int len;

        byte[] buf = new byte[1024];

        while ((len=is.read(buf))!=-1){
            System.out.println(new String(buf,0,len));
        }
        socket.close();

    }
}

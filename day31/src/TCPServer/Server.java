package TCPServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();



    }

    public static void copy(InputStream is, OutputStream out) throws IOException{
        int len =0;
        byte[] bytes = new byte[1024];
        while ((len=is.read(bytes))!=-1){
            out.write(bytes,0,len);
        }
    }
}


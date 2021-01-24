package tcp1;


import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class c {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1",8888);

        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        FileInputStream fis = new FileInputStream("d:\\pic\\bbb.png");

        int len;
        byte[] bytes = new byte[1024];
        while ((len=fis.read(bytes))!=-1){
            out.write(bytes,0,len);
            socket.shutdownOutput();
        }

        while ((len=in.read(bytes))!=-1){
            System.out.println(new java.lang.String(bytes));
        }

        fis.close();
        out.close();
        socket.close();
    }
}

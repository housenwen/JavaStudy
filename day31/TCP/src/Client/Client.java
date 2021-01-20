package Client;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        FileInputStream fis = new FileInputStream("d:\\pic\\bbb.png");

        uploadFile(socket, out, in, fis);
        bw.write("文件上传成功");

    }
    private static void uploadFile(Socket socket, OutputStream out, InputStream in, FileInputStream fis) throws IOException {
        copy(fis,out);

        socket.shutdownOutput();
        int len;
        byte[] buf = new byte[1024];
        while ((len=in.read(buf))!=-1){
            String msg = new String(buf,0,len);
            System.out.println("收到服务端消息："+msg);
        }
    }

    public static void copy(InputStream in, OutputStream out) throws IOException{
        int len=0;
        byte[] bytes = new byte[1024];
        while ((len=in.read(bytes))!=-1){
            out.write(bytes,0,len);
        }
    }
}

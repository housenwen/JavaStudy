package tcp2;

import java.io.*;
import java.net.Socket;

public class c {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9999);
        FileInputStream fileInputStream = new FileInputStream("d:\\pic\\bbb.png");

        BufferedInputStream bis = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len=bis.read(bytes))!=-1){
            bos.write(bytes,0,len);
            bos.flush();
        }
        socket.shutdownOutput();
        System.out.println("文件上传成功");

        InputStream in = socket.getInputStream();
        byte[] back = new byte[1024];
//        in.read(back);
//        System.out.println(new String(back));
        int l;
        while ((l=in.read(back))!=-1){
            System.out.println(new String(back));
        }
        in.close();

        bos.close();
        socket.close();
        bis.close();
    }

}

package TCPUpload;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // 创建客户的socket
        Socket socket = new Socket("127.0.0.1",7777);
        // 获得socket输出流
        OutputStream out = socket.getOutputStream();
        // 创建文件字节输入流
        FileInputStream fis = new FileInputStream(
                "d:/pic/psc.jfif");
        byte[] bytes = new byte[1024];
        int len;
        while ((len=fis.read(bytes))!=-1){
            // 通过out向服务器端输出文件数据
            out.write(bytes,0,len);
        }
        // 客户端发送数据完毕，结束Socket输出流的写入操作，告知服务器端
        socket.shutdownOutput();
        System.out.println(out);
        // 获得socket输入流对象，读取服务器返回的内容：上传成功
        InputStream in = socket.getInputStream();
        len = in.read(bytes);
        System.out.println(new String(bytes,0,len));
        // 关闭资源
        socket.close();
        fis.close();
    }
}

package TCP2;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //1. 创建Socket对象指定服务端的IP和端口号
        Socket socket = new Socket("127.0.0.1",7777);

        //2. 创建本地的输入流，关联要上传的文件
        FileInputStream fis = new FileInputStream("student");

        //3. 获取网络的输出流，用来发送文件字节数据到网络中
        OutputStream os = socket.getOutputStream();

        //4. 边读（读取本地文件）边写（使用网络输出流发送数据）
        int len;
        byte[] bytes = new byte[1024];
        while ((len=fis.read(bytes))!=-1){
            os.write(bytes,0,len);
        }
        //5. 调用shutdownOutput方法告诉服务端，数据传输完毕
        socket.shutdownOutput();

        //6. 获取网络输入流，读取服务端发送的数据
        InputStream is = socket.getInputStream();
        while ((len=is.read(bytes))!=-1){
            String msg = new String(bytes,0,len);
            System.out.println("服务器的回复："+msg);
        }
        //7. 释放客户端资源和本地流资源
        socket.close();
        fis.close();
    }
}

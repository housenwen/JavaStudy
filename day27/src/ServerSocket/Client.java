package ServerSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        //1. 创建Socket对象并指定服务端的IP和端口
        Socket socket = new Socket("127.0.0.1", 8888);
        //2. 获取网络输出流：getOutputStream
        OutputStream netOut = socket.getOutputStream();
        //3. 使用网络输出流给服务端发送一个字符串
        netOut.write("在吗，约不约！".getBytes());
        //4. 当发送完数据后，告诉服务端，发送完毕：shutdownOutput()
        socket.shutdownOutput();

        //5. 获取网络输入流，读取服务端发送的数据:getInputStream（）
        InputStream netIn = socket.getInputStream();
        int len;
        byte[] buf = new byte[1024];
        while ((len = netIn.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
        }

        //6. 释放资源
        socket.close();
    }
}

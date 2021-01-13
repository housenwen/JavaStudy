package ServerSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class test2 {
    public static void main(String[] args) throws IOException {
        //1. 创建服务端ServerSocket对象，绑定端口号
        ServerSocket ss = new ServerSocket(8888);

        while (true) {
            //2. 调用accept方法，等待客户端连接。
            System.out.println("等待客户端连接");
            Socket socket = ss.accept();
            System.out.println("客户端连接成功" + socket);
            //3. 当accept解阻塞，意味着有客户端连接，得到一个Socket对象

            //4. 获取网络输入流，读取客户端发送的数据

            InputStream is = socket.getInputStream();
            int len;
            byte[] bytes = new byte[1024];

            while ((len = is.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }

            //5. 获取网络输出流，写数据给客户端
            OutputStream os = socket.getOutputStream();

            os.write("没空不约，我要学习，等我工作毕业再约！".getBytes());
            //6. 告诉客户端，写数据完毕
            socket.shutdownOutput();
            //7. 释放资源
            socket.close();
            //serverSocket.close();  //如果循环服务，服务器是不能关的
        }
    }
}


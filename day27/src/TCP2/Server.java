package TCP2;

import com.sun.org.apache.xpath.internal.operations.String;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //1. 创建服务端对象，绑定端口
        ServerSocket ss = new ServerSocket(7777);

        //2. 接受客户端连接：accept方法
        System.out.println("等待客户端连接：");
        Socket socket = ss.accept();

        //3. 如果连接成功得到一个Socket对象，可以与客户端进行通讯
        System.out.println("客户端连接成功！"+socket);

        //4. 先获取网络的输入流，用来读取客户端上传的文件信息
        InputStream is = socket.getInputStream();

        //5. 创建一个本地的输出流，用来保存客户端上传的文件数据到服务端的文件
        FileOutputStream fos = new FileOutputStream("student");

        //6. 边读（读取网络中数据），边写（保存到本地硬盘）
        int len;
        byte[] bytes = new byte[1024];
        while ((len=is.read(bytes))!=-1){
            fos.write(bytes,0,len);
        }

        //7. 获取网络的输出流，用来发送信息给客户端
        OutputStream os = socket.getOutputStream();
        os.write("恭喜，文件上传成功！".getBytes());

        //8. 告诉对方发完：shutdownOutput
        socket.shutdownOutput();

        //9. 释放资源
        socket.close();
        fos.close();
        //服务端
        ss.close();
    }
}

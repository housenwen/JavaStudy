package tcp6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class c {
    public static void main(String[] args) throws IOException {
        // 创建客户端socket
        Socket socket = new Socket("127.0.0.1", 9999);
        // 创建文件输出流对象
        FileOutputStream fos = new FileOutputStream("d:/pic/123.png");
        // 获得输入流对象
        InputStream in = socket.getInputStream();
        // 使用循环读取服务器返回的数据
        byte[] buf = new byte[1024];
        int len = -1;
        while((len = in.read(buf)) != -1) {
            fos.write(buf,0,len);

        }
                    socket.shutdownOutput();

        // 关闭资源
        socket.close();
        fos.close();
    }
}

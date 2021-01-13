package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/**
客户端： 给服务端发送一个字符串，并接收服务端回发的字符串
 */
public class test {
    public static void main(String[] args) throws IOException {
        //1. 创建Socket对象并指定服务端的IP和端口
        Socket socket = new Socket("127.0.0.1", 9999);
        System.out.println(socket);
        //2. 获取网络输出流：getOutputStream

        OutputStream os = socket.getOutputStream();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //3. 使用网络输出流给服务端发送一个字符串
            os.write("早上好，很高兴认识你！".getBytes());

            //4. 当发送完数据后，告诉服务端，发送完毕：shutdownOutput()
            socket.shutdownOutput();

            //5. 获取网络输入流，读取服务端发送的数据:getInputStream（）
            InputStream is = socket.getInputStream();
            int len;
            byte[] bytes = new byte[1024];

            while ((len = is.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }
            //6. 释放资源
//            socket.close();
        }
    }

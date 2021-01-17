package TCPUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // 创建服务器socket对象
        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("服务器启动，等待客户端连接！");
        // 死循环保证服务器不退出
        while (true){
            // 获得socket对象
            Socket socket = serverSocket.accept();
            try{
                // 创建目标文件对象，用来保存上传的文件
                File dir = new File("e:\\pic");
                dir.mkdirs();
                // 文件名：域名+当前时间毫秒值+6位随机数.png
                String fileName = System.currentTimeMillis()+".png";
                FileOutputStream fos = new FileOutputStream(
                        new File(dir,fileName));
                // 通过socket对象获得字节输入流对象
                InputStream in = socket.getInputStream();
                // 读取客户端上传的图片数据
                int len = 0;
                byte[] bytes = new byte[1024];
                while ((len=in.read(bytes))!=-1){
                    fos.write(bytes,0,len);
                }

                // 向客户端写出：上传成功
                String str = "上传图片成功";
                socket.getOutputStream().write(str.getBytes());
                System.out.println(str);
                // 关闭资源
                socket.close();
                fos.close();
            }catch (Exception e){

            }
        }
    }
}

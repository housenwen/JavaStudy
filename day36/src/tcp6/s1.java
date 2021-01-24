package tcp6;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class s1 {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(new DownLoadRunnable(socket)).start();
        }
    }
    static class DownLoadRunnable implements Runnable{

        HashSet<String> ips = new HashSet<String>();

        Socket socket;

        public DownLoadRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try {
                OutputStream os = socket.getOutputStream();
                // 创建本地输入流对象
                FileInputStream fis = new FileInputStream("d:/pic/bbb.png");
                // 循环读取文件数据
                int len = -1;
                byte[] buf = new byte[1024];
                while((len = fis.read(buf)) != -1) {
                    // 向客户端写出图片数据
                    os.write(buf,0,len);
                }
                String ip = socket.getInetAddress().getHostAddress();
                if(ips.add(ip)) {
                    System.out.println("恭喜" + ip + "同学，下载成功！！ 当前下载的人数是：" + ips.size());
                }
                // 释放资源
                fis.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }}

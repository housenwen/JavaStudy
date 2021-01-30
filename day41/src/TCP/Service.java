package TCP;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 需求：编写客户端和服务器端程序，客户端用于上传图片，服务器端用于接收文件，
 * 文件上传成功后，给客户端一个反馈：文件上传成功。
 * 注意：
 * 服务器端采用多线程实现，并且要解决文件名冲突问题。
 */
public class Service {
    public static void main(String[] args) throws Exception{

        ExecutorService pool = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("等待客户端连接：");
        Socket socket = serverSocket.accept();
        pool.submit(new MyRunnable(socket));

    }

    static class MyRunnable implements Runnable{

        private Socket socket;

        public MyRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();

                String fileName = System.currentTimeMillis()+".png";
                FileOutputStream fos = new FileOutputStream(fileName);

                byte[] bytes = new byte[1024];
                int len = 0;

                while ((len=in.read(bytes))!=-1){
                    fos.write(bytes,0,len);
                }
                fos.close();

                out.write("文件上传成功".getBytes());
                socket.shutdownOutput();

            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}

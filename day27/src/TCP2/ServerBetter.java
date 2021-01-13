package TCP2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerBetter {
    public static void main(String[] args) throws IOException {

        //线程池处理上传任务
        //1.先创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);

        //2.将文件上传的业务，封装成为一个任务，提交给线程池
        ServerSocket ss = new ServerSocket(7777);
        while (true) {
            System.out.println("等待客户端的连接！");
            Socket socket = ss.accept();
//            System.out.println("客户端已连接"+socket);

            //创建上传任务，将任务提交给线程池
            pool.submit(new UploadTask(socket));
        }
    }


   static class UploadTask implements Runnable {
        private Socket socket;

        public UploadTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            FileOutputStream fos = null;
            try {
                //3. 如果连接成功得到一个Socket对象，可以与客户端进行通讯
                System.out.println("客户端连接成功！");
                //4. 先获取网络的输入流，用来读取客户端上传的文件信息
                InputStream is = socket.getInputStream();

                //5. 创建一个本地的输出流，用来保存客户端上传的文件数据到服务端的文件
                long start = System.currentTimeMillis();
                fos = new FileOutputStream("student");

                //6. 边读（读取网络中数据），边写（保存到本地硬盘）
                int len;
                byte[] bytes = new byte[1024];
                while ((len=is.read(bytes))!=-1){
                    fos.write(bytes,0,len);
                }
                //7. 获取网络的输出流，用来发送信息给客户端
                OutputStream os = socket.getOutputStream();
                os.write("文件上传成功".getBytes());

                //8. 告诉对方发完：shutdownOutput
                socket.shutdownOutput();

                //9. 释放资源
                // socket.close();
                //localOut.close();

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                try{
                    if (fos!=null)
                        fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
    }
}

package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test2_1FileUpload_Server {
    public static void main(String[] args) throws IOException, Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        while (true) {
            System.out.println("等待浏览器连接");
            Socket socket = serverSocket.accept();
            MyRunnable runnable = new MyRunnable(socket);
            pool.submit(runnable);

        }
    }

    static class MyRunnable implements Runnable {
        private Socket socket;

        public MyRunnable(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (
                    //3.1 获取输入流对象
                    BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                    //3.2 创建输出流对象, 保存到本地 .
                    FileOutputStream fis = new FileOutputStream(System.currentTimeMillis() + ".txt");
                    BufferedOutputStream bos = new BufferedOutputStream(fis);
            ) {
                // 3.3 读写数据
                byte[] b = new byte[1024 * 8];
                int len;
                while ((len = bis.read(b)) != -1) {
                    bos.write(b, 0, len);
                }

                // 4.=======信息回写===========================
                System.out.println("back ........");
                OutputStream out = socket.getOutputStream();
                out.write("上传成功".getBytes());
                out.close();
                //================================

                //5. 关闭 资源
                bos.close();
                bis.close();
                socket.close();
                System.out.println("文件上传已保存");
            } catch (IOException e) {

            } finally {

            }
        }
    }
}


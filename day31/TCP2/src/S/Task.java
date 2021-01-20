package S;

import java.io.*;
import java.net.Socket;

public class Task implements Runnable {
    private Socket socket;

    public Task(Socket socket) {
    }

    @Override
    public void run() {
        FileInputStream localIn = null;
        try {
            //获取浏览器传输的数据
            //需要第一行：GET /day12/web/index.html HTTP/1.1的 中间路径字符串
            InputStream netIn = socket.getInputStream();
            //1.先把字节输入流转换为字符流
            InputStreamReader isr = new InputStreamReader(netIn);
            //2.将字符流包装成为高效流
            BufferedReader br = new BufferedReader(isr);
            //3.读取第一行，解析中间的资源路径
            String firstLine = br.readLine();
            //浏览器需要的文件路径
            String path = firstLine.split(" ")[1].substring(1);

            System.out.println("浏览器要：" + path);
            //将文件发送给浏览器
            //1.获取网络输出流，用来发送数据给浏览器
            OutputStream netOut = socket.getOutputStream();

            //2.创建一个本地输入流，关联浏览器需要的文件
            localIn = new FileInputStream(path);
            //3.边读，边写
            //给浏览器发送内容数据之前，需要给浏览器发送协议数据
            netOut.write("\"HTTP/1.1 200 OK\\r\\n".getBytes());
            netOut.write("Content-Type:text/html\r\n".getBytes());
            netOut.write("\r\n".getBytes());

            int len;
            byte[] bytes = new byte[1024];
            while ((len = localIn.read(bytes)) != -1) {
                netOut.write(bytes, 0, len);
            }
            socket.shutdownOutput();

        } catch (Exception e) {
            System.out.println("请求出错：" + e.getMessage());

        } finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (localIn != null) {
                    localIn.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}







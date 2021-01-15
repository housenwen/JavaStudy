package TCP;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Task3_1Web implements Runnable{
    private Socket socket;

    public Task3_1Web(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //转换流,读取浏览器请求第一行
            BufferedReader reader = new
                    BufferedReader(new InputStreamReader(socket.getInputStream()));
            String requst =reader.readLine();
            //取出请求资源的路径
            String[] strings = requst.split(" ");
            System.out.println(Arrays.toString(strings));
            String path  = strings[1].substring(1);
            System.out.println(path);
            FileInputStream fis = new FileInputStream(path);
            System.out.println(fis);
            byte[] bytes = new byte[1024];
            int len = 0;

            //向浏览器 回写数据

            OutputStream out = socket.getOutputStream();
            out.write("HTTP/1.1 200 OK\\r\\n".getBytes());
            out.write("Content-Type:text/html\r\n".getBytes());
            out.write("\r\n".getBytes());

            while ((len=fis.read(bytes))!=-1){
                out.write(bytes,0,len);
            }
            fis.close();
            out.close();
            reader.close();
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

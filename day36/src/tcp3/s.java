package tcp3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class s {
    public static void main(String[] args) throws Exception{
        ServerSocket service = new ServerSocket(7777);
        while (true){
            Socket socket = service.accept();
            new Thread(new MyRunnable(socket)).start();
        }
    }

    public static class MyRunnable implements Runnable{

        private Socket socket;

        public MyRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String requst = br.readLine();

                String[] strArr = requst.split(" ");
                System.out.println(Arrays.toString(strArr));
                String path = strArr[1].substring(1);
                System.out.println(path);

                FileInputStream fis = new FileInputStream(path);
                System.out.println(fis);
                byte[] bytes = new byte[1024];
                int len = 0;
                OutputStream out = socket.getOutputStream();
                out.write("HTTP/1.1 200 OK\r\n".getBytes());
                out.write("Content-Type:text/html\r\n".getBytes());
                out.write("\r\n".getBytes());
                while ((len = fis.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
                fis.close();
                out.close();
                br.close();
                socket.close();
             }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}

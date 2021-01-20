package Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("客户端已启动，等待客户端链接");
        while (true){

        Socket socket = serverSocket.accept();
        System.out.println("客户端已成功连接：" + socket);

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

//            uploadFile(socket, out, in);
            threadPool.submit(new UploadTask(socket));

//            String line =  br.readLine();
//            System.out.println(line);

        }
    }

   public static class UploadTask implements Runnable{
        private Socket socket;

       public UploadTask(Socket socket) {
           this.socket = socket;
       }

       @Override
       public void run() {
           FileOutputStream localOut = null;
           try {
               InputStream inNet = socket.getInputStream();
               long t = System.currentTimeMillis();
               localOut =new FileOutputStream("\\Picture\\"+t+".png");
               copy(inNet,localOut);
               OutputStream netOut = socket.getOutputStream();
               netOut.write("文件上传成功".getBytes());
               socket.shutdownOutput();

           }catch (IOException e){
               e.printStackTrace();
           }
       }
   }

    private static void uploadFile(Socket socket, OutputStream out, InputStream in) throws IOException {
        FileOutputStream localOut = new FileOutputStream("123.jpg");
        copy(in,localOut);
        out.write("上传成功".getBytes());
        socket.shutdownOutput();
    }

    public static void copy(InputStream in, OutputStream out) throws IOException{
        int len=0;
        byte[] bytes = new byte[1024];
        while ((len=in.read(bytes))!=-1){
            out.write(bytes,0,len);
        }
    }
}

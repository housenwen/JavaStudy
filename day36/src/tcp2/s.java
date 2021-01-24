package tcp2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class s {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(() ->{
                try {
                    BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                    FileOutputStream fos = new FileOutputStream(
                            System.currentTimeMillis()+".jpg");
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    byte[] bytes = new byte[1024];
                    int len =0;
                    while ((len=bis.read(bytes))!=-1){
                        bos.write(bytes,0,len);
                    }
                    System.out.println("back.....");
                    OutputStream os = socket.getOutputStream();
                    os.write("上传成功".getBytes());
                    os.close();
                    bos.close();
                    bis.close();
                    socket.close();
                    System.out.println("文件保存成功");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

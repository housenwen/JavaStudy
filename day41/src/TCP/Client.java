package TCP;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("127.0.0.1",9999);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("请输入上传文件的路径：");
            String url = sc.nextLine();
            File file = new File(url);
            if (!file.exists()){
                System.out.println("请输入正确的图片文件路径，请重新输入！");
            }else if (file.isDirectory()){
                System.out.println("请输入正确的图片文件路径，请重新输入！");
            }else {

                FileInputStream fis = new FileInputStream(file);

                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len=fis.read(bytes))!=-1){
                    out.write(bytes,0,len);
                }
                socket.shutdownOutput();

                byte[] bytes1 = new byte[1024];
                int len1 = 0;
                while ((in.read(bytes1))!=-1){
                    System.out.println("服务器的反馈"+new String(bytes1));
                }

                break;
            }

        }
    }
}

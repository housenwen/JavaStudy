package TCPClient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1",9999);
        Scanner scanner  = new Scanner(System.in);

        while (true) {
            System.out.println("******************************");
            System.out.println("请选择服务：");
            System.out.println("1.查看文件\t2.上传文件\t3.下载文件\t4退出程序\t");
            System.out.println("******************************");
            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    scanFile(socket);
                    break;
                case "2":
                    uploadFile(socket);
                    break;
                case "3":
                    downloadFile(socket);
                    break;
                case "4":
                    System.exit(0);
                    break;
                default:
                    System.out.println("其他功能尚在开发中！");

            }

        }

    }

    private static void downloadFile(Socket socket) {

    }

    private static void uploadFile(Socket socket) {

    }

    private static void scanFile(Socket socket) throws IOException{

        InputStream netIn = socket.getInputStream();
        OutputStream netOut = socket.getOutputStream();

        InputStreamReader isr = new InputStreamReader(netIn);
        BufferedReader br = new BufferedReader(isr);

        String firstName = br.readLine();
        String content;

        if (firstName!=null){

        }
    }

    public static void copy(InputStream is, OutputStream out) throws IOException{
        int len =0;
        byte[] bytes = new byte[1024];
        while ((len=is.read(bytes))!=-1){
            out.write(bytes,0,len);
        }
    }
}
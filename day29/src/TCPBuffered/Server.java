package TCPBuffered;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动。。。等待客户端连接。。。");
        try {
            // 创建键盘录入对象
            Scanner sc = new Scanner(System.in);
            //创建服务端socket
            ServerSocket serverSocket = new ServerSocket(8888);
            //得到客户端对应的对象
            Socket socket = serverSocket.accept();
            // 字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 字符输出流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //todo 字节输出流 -> 写入到txt文本
            BufferedOutputStream bs = new BufferedOutputStream(
                    new FileOutputStream("TalkRemember.txt",true));

            while (true) {
                //接收对方的数据
                System.out.println("客户端发送消息：" + br.readLine()); // 你好
                System.out.println("服务器回复消息：");
                String words = sc.nextLine();
                if ("exit".equals(words)) {  //只要说了exit，结束循环
                    break;
                }
                //todo 写入数据到文本
                String str = "服务器回复消息："+words;
                bs.write(str.getBytes());
                bs.write("\r\n".getBytes());
                bs.flush();
                //发送给对方
                bw.write(words);
                bw.newLine();
                bw.flush();
            }
            //todo 关闭流，释放资源。。
            bs.close();
            br.close();
            bw.close();
            // 断开与客户端的连接
            socket.close();
            // 关闭服务器
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

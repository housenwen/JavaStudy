package TCPBuffered;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // 创建键盘录入对象
            Scanner sc = new Scanner(System.in);
            // 创建Socket对象
            Socket socket = new Socket("127.0.0.1", 8888);
            // 字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 字符输出流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //todo 字节输出流->写入txt文件
            BufferedOutputStream bs = new BufferedOutputStream(

                    new FileOutputStream("TalkRemember.txt",true));
            //无限循环
            while (true) {
                System.out.println("客户端发送消息：");
                // 接收键盘的输入字符串
                String words = sc.nextLine();
                if ("exit".equals(words)) {
                    break;
                }

                //todo 写入txt文件
                String str = "客户端发送消息："+words;
                bs.write(str.getBytes());
                bs.write("\r\n".getBytes());
                bs.flush();
                // 发送数据
                bw.write(words);
                // 换行
                bw.newLine();
                // 一定要flush()
                bw.flush();
                // 收取对方的数据
                System.out.println("服务器回复消息：" + br.readLine());
            }
            //todo 关闭流 释放资源
            bs.close();
            br.close();
            bw.close();
            // 断开连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

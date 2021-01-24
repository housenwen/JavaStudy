package tcp4;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class c {
    public static void main(String[] args) throws IOException {
        try {
            // 创建键盘录入对象
            Scanner sc = new Scanner(System.in);
            // 创建Socket对象
            Socket socket = new Socket("127.0.0.1", 8888);
            // 字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 字符输出流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //无限循环
            while (true) {
                System.out.println("我说：");
                // 接收键盘的输入字符串
                String words = sc.next();
                if ("exit".equals(words)) {
                    break;
                }
                // 发送数据
                bw.write(words);
                // 换行
                bw.newLine();
                // 一定要flush()
                bw.flush();
                // 收取对方的数据
                System.out.println("对方说：" + br.readLine());
            }
            br.close();
            bw.close();
            // 断开连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


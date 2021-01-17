package TCPBuffered;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        sendMessage();
    }

    static void sendMessage() throws IOException {
        // 创建键盘录入对象
        Scanner sc = new Scanner(System.in);
        // 创建Socket对象
        Socket socket = new Socket("127.0.0.1", 9999);
        // 字符输出流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        while (true) {
            System.out.println(Thread.currentThread().getName() + "线程" + "客户端发送消息：");
            // 接收键盘的输入字符串
            String words = Thread.currentThread().getName() + "线程" + "客户端发送消息："+sc.nextLine();
            if ("exit".equals(words)) {
                break;
            }

            bw.write(words);
            // 换行
            bw.newLine();
            // 一定要flush()
            bw.flush();
        }
        //todo 关闭流 释放资源
        bw.close();
        // 断开连接
        socket.close();
    }

    static class MyRunnable implements Runnable {
        Object lock = new Object();
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    sendMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

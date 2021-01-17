package TCPBuffered;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动。。。等待客户端连接。。。");
        //创建服务端server
        ServerSocket serverSocket = new ServerSocket(9999);
        //得到客户端对应的对象
        Socket socket = serverSocket.accept();
        // 字符输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 字符输出流
        BufferedWriter bw = new BufferedWriter(new FileWriter("MsgRemember.txt", true));

        //todo 字节输出流 -> 写入到txt文本
        BufferedOutputStream bs = new BufferedOutputStream(
                new FileOutputStream("MsgRemember.txt", true));

        try {
            while (true) {
                //接收对方的数据
                System.out.println("客户端发送消息：" + br.readLine()); // 你好
                String str = "客户端发送消息：" + br.readLine();
                System.out.println(str);
                if (str.equalsIgnoreCase("exit")) {
                    break;
                }
                bw.write(str);
                bw.newLine();
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //todo 关闭流，释放资源。。
        bw.close();
        bs.close();
        br.close();
        // 断开与客户端的连接
            socket.close();
        // 关闭服务器
//            serverSocket.close();
    }
}
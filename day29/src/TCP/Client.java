package TCP;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * TCP客户端
 */
public class Client {

    public static void main(String[] args) throws IOException {
        setClient();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        MyRunnable myRunnable = new MyRunnable();
        pool.submit(myRunnable);
//        pool.submit(myRunnable);
//        pool.submit(myRunnable);

    }

    private static void setClient() throws IOException {
        //1.创建客户端
        Socket client = new Socket("127.0.0.1",6666);
        System.out.println("已成功连接到服务器...");
        //2.获取通道中的输出流
        OutputStream out = client.getOutputStream();
        String name = Thread.currentThread().getName();
        System.out.println("线程名是："+name);
        //3.调用out的write方法
        String str = "hello.服务器,我是客户端"+name;
        System.out.println("要发送的字符串："+str);
        out.write(str.getBytes());
        System.out.println("数据发送成功....");
        //4.释放资源
        out.close();
        client.close();
        System.out.println("客户端关闭了...");
    }
    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                setClient();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

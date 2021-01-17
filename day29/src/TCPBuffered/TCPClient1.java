package TCPBuffered;

import java.io.IOException;

public class TCPClient1 {
    public static void main(String[] args) throws IOException {
//        TCPClient client = new TCPClient();
//        client.sendMessage();
        TCPClient.MyRunnable myRunnable = new TCPClient.MyRunnable();
        new Thread(myRunnable,"赵四").start();

    }
}

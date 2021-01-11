package ClassWork02;

public class wrok3 {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        Thread t1 = new Thread(ticket,"窗口1");
        Thread t2 = new Thread(ticket,"窗口2");
        Thread t3 = new Thread(ticket,"窗口3");

        t1.start();
        t2.start();
        t3.start();


    }
}

package ClassWork02;

public class work4 {
    public static void main(String[] args) {


        Ballot ballot = new Ballot();

        Thread t1 = new Thread(ballot,"窗口1");
        Thread t2 = new Thread(ballot,"窗口2");
        Thread t3 = new Thread(ballot,"窗口3");

        t1.start();
        t2.start();
        t3.start();


    }
}

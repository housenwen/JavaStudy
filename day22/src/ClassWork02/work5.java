package ClassWork02;

public class work5 {
    public static void main(String[] args) {

        Note note = new Note();

        Thread t1 = new Thread(note,"窗口1");
        Thread t2 = new Thread(note,"窗口2");
        Thread t3 = new Thread(note,"窗口3");

        t1.start();
        t2.start();
        t3.start();


    }
}

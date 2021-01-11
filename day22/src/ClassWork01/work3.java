package ClassWork01;

public class work3 {
    public static void main(String[] args) {

        CountThread c1 = new CountThread(10);
        CountThread c2 = new CountThread(5);
        CountThread c3 = new CountThread(8);

        c1.start();
        c2.start();
        c3.start();

    }
}

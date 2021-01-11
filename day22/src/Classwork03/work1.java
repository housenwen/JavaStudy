package Classwork03;

public class work1 {
    public static void main(String[] args) {

        Seller seller = new Seller();

        Thread t1 = new Thread(seller,"实体店");
        Thread t2 = new Thread(seller,"网点");

        t1.start();
        t2.start();

    }
}

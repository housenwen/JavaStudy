package ClassWork02;

public class work1 {
    public static void main(String[] args) {
        RunnableImp r = new RunnableImp();

        Thread t1 = new Thread(r,"实体店");
        Thread t2 = new Thread(r,"网点");


        t1.start();
        t2.start();
    }
}

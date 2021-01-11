package ClassWork01;

public class work1 {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();

        myThread.start();

        RunnableImp runnableImp = new RunnableImp();

        new Thread(runnableImp).start();
//        runnableImp.run();

    }
}

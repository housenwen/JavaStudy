package Thread;

public class RunnableImp implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Runnable中执行"+i);
        }
    }
}

package Thread;

public class Hthread extends Thread{
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread中执行："+i);
        }
    }
}

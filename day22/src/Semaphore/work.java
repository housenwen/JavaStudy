package Semaphore;

public class work {
    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 5; i++) {
            //启动5个线程
            ThreadA a = new ThreadA(service);
            a.setName("线程"+i);
            a.start();//5个线程会同时执行Service的testMethod方法，而某个时间段只能有1个线程执行
            
        }
    }
}

package CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class work {
    public static void main(String[] args) {
        //等待5个线程执行完毕，再执行MeetingThread
        CyclicBarrier cbRef = new CyclicBarrier(5, new MeetingThread());

        PersonThread p1 = new PersonThread(cbRef);
        PersonThread p2 = new PersonThread(cbRef);
        PersonThread p3 = new PersonThread(cbRef);
        PersonThread p4 = new PersonThread(cbRef);
        PersonThread p5 = new PersonThread(cbRef);
        PersonThread p6 = new PersonThread(cbRef);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();

    }
}

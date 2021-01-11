package ClassWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hall implements Runnable{
    private int note = 10000;
//    private Map<Integer, AtomicInteger> map = new HashMap<>();
//    private Map<Integer, AtomicInteger> map = new ConcurrentHashMap<>();
    private Map<Integer, AtomicInteger> map = new Hashtable<>();

    {
        for (int i = note; i > 0; i--) {
            map.put(i,new AtomicInteger(0));
        }
    }
//    final Object lock = new Object();

//    Lock lock = new ReentrantLock();

    @Override
    public synchronized void run() {
        while (true) {
//            synchronized (lock) {
//            lock.lock();
                if (note > 0) {

                    int num = note;
                    // 卖掉的票号放入集合, 计数加1
                    map.get(num).incrementAndGet();

                    System.out.println(Thread.currentThread().getName() + "正在卖" + num);

                    note--;

                } else {
                    break;
                }
//                lock.unlock();
            }
        }
//    }
    public void check(){

        for (Map.Entry<Integer,AtomicInteger> entry: map.entrySet()){

            if (entry.getValue().get()>1){
                System.out.println("重复卖出的号码："+entry.getKey());
            }else if (entry.getValue().get()==0){
                System.out.println("没有卖出的票号："+entry.getKey());
            }

        }

    }
}

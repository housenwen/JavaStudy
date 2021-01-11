package ClassWork;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class TicketHall implements Runnable {
    // 剩余票量
    private int ticket = 100;

    // 正确的票集合，key 包含从 1000 到 1 的一千张票，value 记录卖出去几次
    private Hashtable<Integer, AtomicInteger> correct = new Hashtable<>();

    {
        for (int i = ticket; i > 0; i--) {
            correct.put(i, new AtomicInteger(0));
        }
    }

    @Override
    public void run() {
        sell();
    }

    // 买票方法
    public void sell() {
        while (true) {
            if (ticket > 0) {
                // 票号
                int number = ticket;

                // 卖掉的票号放入集合, 计数加1
                correct.get(number).incrementAndGet();

                System.out.println(Thread.currentThread().getName() + "正在卖:" + number);
                // 卖过了减 1
                ticket--;
            } else {
                break;
            }
        }
    }

    // 打印有问题的票集合
    public void check() {
        for (Map.Entry<Integer, AtomicInteger> entry : correct.entrySet()) {
            if (entry.getValue().get() > 1) {
                System.out.println("重复卖出去的票号:" + entry.getKey());
            }
            if (entry.getValue().get() == 0) {
                System.out.println("没卖出去的票号:" + entry.getKey());
            }
        }
    }

}
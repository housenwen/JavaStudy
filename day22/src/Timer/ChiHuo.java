package Timer;

import java.util.List;

public class ChiHuo extends Thread {
    private List<String> list;

    public ChiHuo(String name, List<String> list) {
        super(name);
        this.list = list;
    }

    @Override

    public void run() {
        while (true) {
            synchronized (list) {
                if (list.size() == 0) {

                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //如果集合中有元素 则获取元素的线程获取元素(删除)
                //打印集合 集合中没有元素了
                list.remove(0);
                System.out.println(list);
//集合中已经没有元素 则唤醒添加元素的线程 向集合中添加元素
                list.notify();
            }
        }
    }
}

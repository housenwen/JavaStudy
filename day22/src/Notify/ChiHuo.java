package Notify;

import Timer.Baozipu;

import java.util.ArrayList;
import java.util.List;

public class ChiHuo extends Thread {

    private List<String> list = Baozipu.list;

    public ChiHuo(String name, List<String> list){
        super(name);
        this.list = list;
    }

    @Override
    public void run() {
        int index = 1;
        //为了能看到效果 写个死循环
        while(true){
            //由于使用的同一个集合 list作为锁对象
            synchronized (list){
                //如果集合中没有元素 获取元素的线程进入到等待状态
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if(list.size()==0){
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //如果集合中有元素 则获取元素的线程获取元素(删除)
                list.remove(0);
                //打印集合 集合中没有元素了
                System.out.println("吃货吃了"+(list.size()+index++));
                //集合中已经没有元素 则唤醒添加元素的线程 向集合中添加元素
                list.notify();
            }
        }
    }
}


package Notify;

import java.util.ArrayList;
import java.util.List;

public class BaoZiPu extends Thread{
    private List<String> list ;
    public BaoZiPu(String name, List<String> list){
        super(name);
        this.list = list;
    }
    @Override
    public void run() {
        int i = 0;
        while(true){
            //list作为锁对象
            synchronized (list){

                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                if(list.size()>0){
                    //存元素的线程进入到等待状态
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //如果线程没进入到等待状态 说明集合中没有元素
                //向集合中添加元素
                list.add("包子"+i++);
                System.out.println(list);
                //集合中已经有元素了 唤醒获取元素的线程
                list.notify();
            }
        }
    }
}

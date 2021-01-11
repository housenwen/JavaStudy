package Timer;

import java.util.List;

public class Baozipu extends Thread{

    public static List<String> list;

    public Baozipu(String name, List<String> list) {
        super(name);
        this.list = list;
    }

    @Override
    public void run(){

        int index = 1;

        while (true){

            //list作为锁对象

            synchronized (list){
                if (list.size()>0){
                    try {

                        list.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                //如果线程没进入到等待状态 说明集合中没有元素
                //向集合中添加元素
                list.add("包子"+index++);
                System.out.println(list);
//集合中已经有元素了 唤醒获取元素的线程
                list.notify();

            }

        }

    }
}

package Thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChildThread extends Thread{

    /**注意 SimpleDateFormat 不是线程安全，现在是一个线程使用它因此没有并发问题
     * 日期格式：SDF
    */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void run(){

        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            // 间隔 1s =1000毫秒
            try{
                Thread.sleep(1000);//sleep什么意思？
            }catch (InterruptedException e){//为什么要有这个异常接受？这个异常叫什么？
                System.out.println(e);
                e.printStackTrace();
            }

            System.out.println(sdf.format(date));
        }

    }
}

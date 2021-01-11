package ClassWork02;

public class RunnableImp implements Runnable {

    private int count = 100;
    private String name ;

    public RunnableImp(int count, String name) {
        this.count = count;
        this.name = name;
    }

    public RunnableImp() {
    }

    @Override
    public void run() {
        // 定义变量统计卖出去的个数
        int sellCount = 0;

        while (true) {
            if (count < 1) {
                break;
            } else {
                // 每卖出一个count-1
                count--;
                // 线程每卖出一个把卖出的数量+1
                sellCount ++;
                System.out.println(Thread.currentThread().getName() +
                        "路程卖杯子：" + sellCount+"还剩："+count);
            }

            // 为了更好看到竞争资源,睡一小下
         try {
            Thread.sleep(100);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }

        }
    }
}

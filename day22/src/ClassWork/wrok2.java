package ClassWork;

public class wrok2 {
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        // t1 线程让 i 自增 50000 次
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int j = 0; j < 50000; j++) {
                    i++;
                }
            }
        };

        // t2 线程让 i 自增 50000 次
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int j = 0; j < 50000; j++) {
                    i++;
                }
            }
        };

        t1.start();
        t2.start();
        t1.join(); // main 线程等待 t1 运行结束
        t2.join(); // main 线程等待 t2 运行结束
        // 最终正确结果应当是 100000, 但由于 i++ 不是原子性导致结果一般都是小于 100000
        // 要让效果明显 1.调大数字， 2.增多线程  3. 每次i++时随机睡眠一小会
        System.out.println(i);
    }
}

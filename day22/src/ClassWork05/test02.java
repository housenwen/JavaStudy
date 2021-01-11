package ClassWork05;

class Tunnel implements Runnable {
    // 1.1 定义一个变量，用来记录通过隧道的人数
    private int crossNum = 0;

    /*
     * 1.2 重写Runnable的run方法
     */
    @Override
    public void run() {
        // 1.4 调用通过隧道的方法
        cross();
    }

    /*
     * 1.3 定义一个同步方法，模拟每个人通过隧道需要5秒钟
     */
    public synchronized void cross() {
        // 1.3.1 子线程睡眠5秒钟，模拟每个人通过隧道需要5秒钟
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 1.3.2 改变通过的人次
        crossNum++;
        // 1.3.3 打印线程名称及其通过隧道的顺序，模拟人通过隧道及其顺序
        System.out.println(Thread.currentThread().getName() + "已经通过隧道，TA是第" + crossNum + "通过的！");
    }}
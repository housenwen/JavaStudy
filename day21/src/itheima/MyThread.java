package itheima;

public class MyThread extends Thread{
    //定义指定线程名称的构造方法
    public MyThread(String name) {
        //调用父类的String参数的构造方法，指定线程的名称
        super(name);
    }
    public MyThread() {
        //不指定线程的名字,线程有默认的名字Thread-0
    }
    /**
     * 重写run方法，完成该线程执行的逻辑
     */
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println(getName()+"：正在执行！"+i);
        }
    }
}

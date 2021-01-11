package itheima;

public class test06 {
    public static void main(String[] args) {
        //创建自定义线程对象
        MyThread mt = new MyThread("新的线程！");
        //开启新线程
        mt.start();
        //在主方法中执行for循环
        for (int i = 0; i < 200; i++) {
            System.out.println("main线程！"+i);
        }
    }
}

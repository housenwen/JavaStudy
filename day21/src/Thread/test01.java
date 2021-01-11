package Thread;
/**请使用继承Thread类的方式定义一个线程类，在run()方法中使用循环变量i遍历1--1000所有的数字。

 定义main()方法，启动这个线程，然后再使用循环变量k遍历1--1000所有的数字。

 请观察控制台打印，i和k交叉打印的情况

 */

public class test01 {
    public static void main(String[] args) {

        Hthread hthread = new Hthread();

        hthread.run();

        for (int i = 0; i < 100; i++) {
            System.out.println("主程序运行：" + i);
        }

    }

}

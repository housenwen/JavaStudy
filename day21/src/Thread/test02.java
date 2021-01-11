package Thread;

/**请使用实现Runnable接口的方式定义一个类，在run()方法中使用循环变量i遍历1--1000所有的数字。

 定义main()方法，启动这个线程，然后再使用循环变量k遍历1--1000所有的数字。

 请观察控制台打印，i和k交叉打印的情况。
 *
 */
public class test02 {
    public static void main(String[] args) {
// 启动自定义线程
        RunnableImp runnableImp = new RunnableImp();

        new Thread(runnableImp).start();

//        new RunnableImp().run();//先运行此段代码，后运行main的for循环
        // 主线程输出
        for (int i = 0; i < 1000; i++) {

            System.out.println("main方法执行"+i);
        }

    }
}

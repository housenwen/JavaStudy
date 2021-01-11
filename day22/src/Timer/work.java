package Timer;

/**
 * public void wait() : 让当前线程进入到等待状态 此方法必须锁对象调用.
 */
public class work {
    public static void main(String[] args) throws InterruptedException {
        // 步骤1 : 子线程开启,进入无限等待状态, 没有被唤醒,无法继续运行.

//        new Thread( () -> {
//            try {
//                System.out.println("begin wait ...");
//                synchronized (""){
//                    "".wait();
//                }
//                System.out.println("over");
//            }catch (Exception e){
//            }
//        }).start();

        new Thread(() -> {
            try {
                System.out.println("begin wait..");
                synchronized ("") {
                    "".wait();
                }
                System.out.println("over");
            } catch (Exception e) {

            }
        }
        ).start();

        Thread.sleep(1000);
        //步骤2:  加入如下代码后, 1秒后,会执行notify方法, 唤醒wait中线程.
        new Thread(() -> {

            try {

                synchronized ("") {
                    System.out.println("唤醒！！");
                    "".notify();
                }

            } catch (Exception e) {


            }


        }).start();

    }
}

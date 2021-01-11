package ClassWork01;

public class work2 {
    public static void main(String[] args) {
       new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("播放背景音乐");
               System.out.println(Thread.currentThread().getName());
           }
       }).start();

       new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("显示画面");
               System.out.println(Thread.currentThread().getName());
           }
       }).start();


    }
}

package Singleton;

/**
 * 懒汉模式
 * 存在线程不安全的问题。
 */
public class Mgr02 {
    private static volatile Mgr02 Instance;
    private Mgr02(){}

    public synchronized static Mgr02 getInstance(){
        if (Instance==null){
            try {
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
            Instance = new Mgr02();
        }
        return Instance;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {

            int finalI = i;
            new Thread(()->{
                System.out.println(Mgr02.getInstance().hashCode());
//                System.out.println(Thread.currentThread().getName());
            }).start();
        }
    }
}

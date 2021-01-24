package Singleton;

/**
 * 静态内部类
 */
public class test {
    public test() {
    }
    private static class testHolder{
        private final static test Instance = new test();

    }
    public static test getInstance(){
        return testHolder.Instance;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(test.getInstance().hashCode());
            }).start();
        }
    }
}

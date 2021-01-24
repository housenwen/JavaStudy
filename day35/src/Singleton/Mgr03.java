package Singleton;

/**
 * 枚举模式，不仅解决线程问题，而且可以防止反序列化
 */
public enum  Mgr03 {
    Instance;
    public void m(){}

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Mgr03.Instance.hashCode());
            } ).start();
        }
    }
}

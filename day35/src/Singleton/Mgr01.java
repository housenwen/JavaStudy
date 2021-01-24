package Singleton;

/**
 * 饿汉式单例模式 JVM保证线路安全
 */
public class Mgr01 {
    private String name;
    private static final Mgr01 Instance = new Mgr01();
    private Mgr01(){}

    public Mgr01(String name) {
        this.name = name;
    }

    public static Mgr01 getInstance(){
        return Instance ;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr01 m1 = Mgr01.getInstance();
        Mgr01 m2 = Mgr01.getInstance();
        System.out.println(m1==m2);
    }

}

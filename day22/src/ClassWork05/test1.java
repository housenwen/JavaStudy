package ClassWork05;

public class test1 {
    public static void main(String[] args) {
        // 2.1 在main方法中创建一个隧道类对象
        Tunnel tul = new Tunnel();

        // 2.2 在main方法中，循环创建10个子线程对象，通过构造方法把隧道对象和// 线程名（作为人的姓名）传递进去，并开启子线程
        for (int i = 1; i <= 10; i++) {
            Thread t = new Thread(tul, "p" + i);
            t.start();
        }
    }
}

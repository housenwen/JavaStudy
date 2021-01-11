package Lambda;

public class test {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("多线程运行")).start();
    }
}

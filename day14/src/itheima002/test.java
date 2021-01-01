package itheima002;


public class test {
    public static void main(String[] args) {

        Phone P1 = new Phone("苹果","黑金",5999.0);
        Phone P2 = new Phone("华为","玫瑰金",9999.0);

        P1.show();
        P2.show();

        System.out.println("------------------------");

        System.out.println(P1.toString());

        System.out.println( P2.toString());

    }
}

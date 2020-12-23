package itheima;

public class test17 {
    //    public static void main(String[] args) {
//        int a = 10;
//        int b = 20;
//        int x = a + b++;
//        System.out.println("b=" + b);
//        System.out.println("x=" + x);
//    }
    public static void main(String[] args) {
//        int x = 4;
//        int y = (--x) + (x--) + (x * 10);
//        System.out.println("x = " + x + ",y = " + y);

        short s = 30;
        int i = 50;
        s += i;
        System.out.println("s=" + s);
        int x = 0;
        int y = 0;
        int z = 0;
        boolean a, b;
        a = x > 0 & y++ > 1;
        System.out.println("a=" + a);
        System.out.println("y=" + y);
        b = x > 0 && z++ > 1;
        System.out.println("b=" + b);
        System.out.println("z=" + z);
        a = x > 0 | y++ > 1;
        System.out.println("a=" + a);
        System.out.println("y=" + y);
        b = x > 0 || z++ > 1;
        System.out.println("b=" + b);
        System.out.println("z=" + z);

    }

}

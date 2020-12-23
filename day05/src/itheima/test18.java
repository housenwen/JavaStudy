package itheima;

public class test18 {
    public static void main(String[] args) {
//        int i = 10, j = 25, x = 30;
//        switch (j - i) {
//            case 15:
//                x++;
//            case 16:
//                x += 2;
//            case 17:
//                x += 3;
//            default:
//                --x;
//                System.out.println(x);.
        int sum=0;
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                sum = sum+ i*j;
                System.out.println(i);
                System.out.println(j);
            }
        }
        System.out.println("sum="+sum);

    }
}

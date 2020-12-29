package itheima;

public class test03 {
    public static void main(String[] args) {
        int x = 6, y = 10, k = 5;
        switch(x % y) {
            case 0:
                k = x * y;
            case 6:
                k = x / y;
            case 7:
                k = x - y;
            default:
                k = x * y - x;
        }
        System.out.println(k);
    }
}

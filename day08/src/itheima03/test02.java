package itheima03;

public class test02 {
    public static void main(String[] args) {
        int[] arr = new int[1];
        demo( arr );
        System.out.println(arr[0]);
    }
    public static void demo( int[] arr ) {
        arr[0] = 10;
    }
}

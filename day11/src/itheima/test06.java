package itheima;

public class test06 {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        for(int i = 0; i < arr.length; i++) {
            if(i == 2) {
                continue;
            }
            if(i == 2) {
                arr[3] = 10;
            }
        }
    }
}

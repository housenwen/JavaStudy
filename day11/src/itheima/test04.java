package itheima;

public class test04 {
    public static void main(String[] args) {
        int[] arr = {10, 20, 40, 30};
        function(arr);
    }

    public static void function(int[] arr) {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                System.out.println(min);
            }
        }

    }
}
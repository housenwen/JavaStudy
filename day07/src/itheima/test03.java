package itheima;

public class test03 {
    public static void main(String[] args) {
        int [] brr = {20,30,50,60,80};
        int max = getMax(brr);
        System.out.println("数组的最大值:"+max);
    }
    public static int getMax(int[] arr) {
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (max<arr[i]){
                max = arr[i];
            }
        }

        return max;
    }
}

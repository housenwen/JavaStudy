package itheima;
//遍历数组的方法
public class test01 {
    public static void main(String[] args) {
        int[] brr = {11,22,33,44,55};
//        System.out.println(brr[4]);
        printArray(brr);
        System.out.println("另一个代码逻辑");

    }
    public static void printArray(int[] arr){
        System.out.print("[");

        for (int i = 0; i < arr.length; i++) {
            if (i==arr.length-1) {
                System.out.println(arr[i]+"]");
            }else {

                System.out.print(arr[i]+",");
            }

        }


    }
}

package itheima;

public class test05 {
    public static void main(String[] args) {
        int[] arr = {5,4,3,2,5};
        int index = function(arr, 3);
        System.out.println(index);
    }
    public static int function(int[] arr,int value) {
        int index = -1;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == value) {
                index = i;
               break;
            }
        }
        return index;
    }
}

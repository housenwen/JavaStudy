package itheima;

public class test {
    public static void main(String[] args) {

        int[] arr = {1,2,3,4};
        int index = -1;
        int element = getElemet(arr,index);

    }

    private static int getElemet(int[] arr, int index) {

        if (index<0||index>arr.length-1){

            throw new ArrayIndexOutOfBoundsException("哥们，角标越界了~~~");

        }

        int element = arr[index];

        return element;

    }
}

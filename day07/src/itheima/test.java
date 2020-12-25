package itheima;

public class test {
    public static void main(String[] args) {

        int [] arr = {11,22,33,44,55};

        Marry(arr);

    }

    public static void Marry (int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"\t");
        }

    }
}

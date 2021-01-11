package ClassWork01;

public class test01 {
    public static void main(String[] args) {
        int[] arr = null;

        try {
            getMax(arr);
        } catch (NullPointerException e) {
            System.out.println(e);
        }

        int[] brr = new int[0];

        try {
            getMax(brr);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
        int[] crr = {1,23,4,56,7,8};
        getMax(crr);

    }

    static void getMax(int[] arr) {
        if (arr == null) {
            throw new NullPointerException("参数为NULL！");
        }
        if (arr.length == 0) {
            throw new ArrayIndexOutOfBoundsException("数组长度为0！");
        }
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {

            if (max < arr[i]) {

                max = arr[i];

            }

        }

        System.out.println("最大值：" + max);

    }
}

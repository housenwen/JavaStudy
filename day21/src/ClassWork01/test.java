package ClassWork01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 请对以下数组进行排序(分别用冒泡排序和选择排序)
 * int[] arr = {1,432,4,325,24,365,25,89,6,3,476,875,846};
 */
public class test {

    public static void main(String[] args) {

        int[] arr = {1,432,4,325,24,365,25,89,6,3,476,875,846};

        //冒泡排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

        //选择排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[i]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

//        数组转集合排序
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
//        Collections.sort(list);
        System.out.println(list);
//        集合转数组
        int[] brr = arr;
        for (int i = 0; i < list.size(); i++) {
//            brr[i] = list.get(i);
        }

        System.out.println(Arrays.toString(brr));

    }

}

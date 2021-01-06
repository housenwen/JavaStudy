package HomeWork;

import java.lang.reflect.Array;
import java.util.*;
//数组集合相互转换：
//集合的倒序和升序
public class test03 {
    public static void main(String[] args) {
        int[] arr = {1,2,432,32,54,32,3,7,657,563,25,43,6,463,52};
        System.out.println(Arrays.toString(arr));
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {

            list.add(arr[i]);

        }

        System.out.println(list);

        Collections.sort(list);

        System.out.println(list);

        ArrayList<Integer> list1 = new ArrayList<Integer>();

        list1.addAll(list);

        Collections.sort(list1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        System.out.println(list1);

        int [] brr = new int[list.size()];

        for (int i = 0; i <= list.size(); i++) {

            for (int j = 0; j <i; j++) {
                brr[j] = list.get(j);
            }
        }

        System.out.println(Arrays.toString(brr));

    }
}

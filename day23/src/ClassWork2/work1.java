package ClassWork2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class work1 {
    public static void main(String[] args) {
        Integer[] arr={9,1,5,2,3,8,6,7,9};

//        Arrays.sort(arr, (Integer a, Integer b) -> a-b);

        Arrays.sort(arr,(o1, o2) -> o2-o1);

        System.out.println(Arrays.toString(arr));

        System.out.println("--------------------");

        ArrayList<Integer> list = new ArrayList<>();

        Collections.addAll(list,9,1,5,2,3,8,6,7,9);

        Collections.sort(list,(o1, o2) -> o1-o2);

        System.out.println(list);

    }
}

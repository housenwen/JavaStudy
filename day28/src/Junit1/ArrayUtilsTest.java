package Junit1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayUtilsTest {

    @Test
    public void getMax() {
        int[] arr = {2,12,34,56,2,31,123,43};
        ArrayUtils au = new ArrayUtils();
        int max=  au.getMax(arr);
        System.out.println("数组最大值："+max);
    }
}
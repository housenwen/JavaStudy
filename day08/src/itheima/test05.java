package itheima;

import java.util.Random;

//3、在主方法中定义一个长度为10的数组，数组中元素使用随机数赋值，
// 随机数范围为23-57（包括23与57）判断偶数的个数，并输出
public class test05 {
    public static void main(String[] args) {
        Random random = new Random();
        int [] brr = new int[10];
//        random.nextInt(57-23+1)+23
        int count = 0;
        for (int i = 0; i < brr.length; i++) {
            brr[i] = random.nextInt(57-23+1)+23;
            System.out.println(brr[i]);
            if (brr[i]%2==0){
                count++;
            }
        }
        System.out.println("数组中偶数的个数："+count);
    }
}

package itheima04;


import java.util.ArrayList;

public class test03 {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList();

        long stare = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {

            list.add(i);
            System.out.println(i);
        }

        long end = System.currentTimeMillis();

        System.out.println("花费时间"+(end-stare)+"毫秒");


    }







}

package com.itheima;

public class test15 {
    public static void main(String[] args) {
//        int i = 10,j = 25,x = 30;
//        switch(j-i) {
//            case 15:
//                x++;
//            case 16:
//                x+=2;
//            case 17:
//                x+=3;
//            default:
//                --x;
//
//                System.out.println(x);

//            int i = 1;
//            while(i <= 10){
//                i++;
//                System.out.println("HelloWorld"+i);
//
//
//
//        }

//        int i = 1;
//        while (i <= 10) {
//            i++;
//            if (i % 2 == 0) {
//                System.out.println("HelloWorld"+ i);
//            }
//        }
//        int sum = 0;
//        int i = 1;
//        while(i <= 5){
//            sum += i;
//            i++;
//        }
//        System.out.println(sum);
//        int i = 1;
//        do {
//            System.out.println("HelloWorld");
//            i++;
//        } while(i < 3);
        int sum=0;
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                sum+=i*j;
            }
        }
        System.out.println("sum="+sum);
    }
    }
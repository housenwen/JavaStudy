package ClassWork01;

import java.util.Random;

public class MyThread extends Thread{

     static int sum = 0;
     Random random = new Random();

     @Override

    public void run(){

         for (int i = 0; i < 10; i++) {

             sum=sum+random.nextInt(1000-100+1)+100;

         }

         System.out.println(Thread.currentThread().getName()+"线程和值为："+sum);
     }


}

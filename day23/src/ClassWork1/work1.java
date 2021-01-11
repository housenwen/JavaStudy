package ClassWork1;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.concurrent.CountDownLatch;

public class work1 {

    private static CountDownLatch cdl = new CountDownLatch(2);

   static int count = 0;



    public  static void main(String[] args) {

        AThread a = new AThread();
        BThread b = new BThread();

        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);

        t1.start();
        t2.start();


        System.out.println(count);

    }
  static class AThread implements Runnable{
       @Override
       public void run() {
           try {
               for (int i = 0; i < 1000; i++) {
                   if (i%3==0&&i%2==0&&i%7==0&&i%5==0){
                       System.out.println(i);
                       cdl.wait();
                   }
               }
           }catch (InterruptedException e){
              e.printStackTrace();
           }
       }
   }
  static   class BThread implements Runnable{

       @Override
       public void run() {
               cdl.countDown();
               count++;
       }
   }
}

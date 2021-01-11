package SafeThread;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class MmThread extends Thread{
    static int[] intArray = new int[1000];//不直接使用数组
    //改用原子类，使用数组构造
    public static AtomicIntegerArray arr = new AtomicIntegerArray(intArray);
    @Override
    public  void run(){
        for (int i = 0; i < intArray.length; i++) {
//            intArray[i]++;
            arr.addAndGet(i,1);
        }

    }
}

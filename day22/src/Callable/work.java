package Callable;

import com.sun.javafx.tk.Toolkit;
import javafx.concurrent.Task;

import java.util.concurrent.*;

public class work {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(1,1,

                0, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

        Future<Integer> future = pool.submit(new Task());

        System.out.println(future.get());

    }

    static class Task implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i <= 100; i++) {
                sum+=i;
            }
            return sum;
        }
    }


}

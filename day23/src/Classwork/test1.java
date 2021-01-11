package Classwork;

import java.util.UnknownFormatConversionException;
import java.util.concurrent.*;

public class test1 {
    public static void main(String[] args) {

        MCallablle myCallablle = new MCallablle();

        ExecutorService pool = Executors.newFixedThreadPool(1);

        Future<Double> f1 = pool.submit(myCallablle);

        try {
            String d = String.format("%.3f", f1.get());
            System.out.println(d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (UnknownFormatConversionException e) {
            e.printStackTrace();
        }
    }

    static class MCallablle<Double> implements Callable<java.lang.Double> {
        @Override
        public java.lang.Double call() throws Exception {
            double sum = 0;
            for (int i = 0; i <= 100; i++) {
                sum += i;
            }
            return sum / 3;

        }
    }
}

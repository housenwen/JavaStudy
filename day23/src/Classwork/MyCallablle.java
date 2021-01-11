package Classwork;

import java.util.concurrent.Callable;

public class MyCallablle<Double> implements Callable<java.lang.Double> {
    @Override
    public java.lang.Double call() throws Exception {

        double sum = 0;

        for (int i = 0; i <=100; i++) {

            sum+=i;

        }
        return sum/3;

    }
}

package SumCallable;

import java.util.concurrent.Callable;

public class SumCallable implements Callable<Integer> {

    private int n;

    public SumCallable(int n) {
        this.n = n;
    }

    @Override
    public Integer call() throws Exception {
        // 求1-n的和?
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        return sum;


    }
}

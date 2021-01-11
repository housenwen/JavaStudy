package Lambda;

public class work1 {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i <= 100; i++) {
                    sum+=i;
                }
                System.out.println(Thread.currentThread().getName()+" "+sum);
            }
        }).start();

        new Thread(()-> {
//            int sum = 0;
//            for (int i = 0; i <= 100; i++) {
//                sum += i;
//            }
//            System.out.println(sum);
            System.out.println(Thread.currentThread().getName()+" 周杰伦");
        }).start();

    }
}

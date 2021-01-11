package ClassWork02;

public class Bill implements Runnable{

    private int bill= 100;

    public Bill() {
    }

    public Bill(String name) {
    }

    @Override
    public void run(){

        int sellBill= 0;

        while (true){
//每个窗口卖票的操作
            //窗口 永远开启
            if (bill>0){
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            bill--;

            sellBill++;

            System.out.println(Thread.currentThread().getName()+"已卖"+sellBill+"还剩"+bill);

            if (bill<0){
                break;
            }
        }

    }
}

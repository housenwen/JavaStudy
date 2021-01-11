package ClassWork05;

public class SuiDao implements Runnable{

    private int num = 100;

    private int count = 0;

    @Override
    public void run() {

        String name = Thread.currentThread().getName();

        while (true){

            synchronized (this){

                if (num>0){

                    try {
                        Thread.sleep(100*5);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    num--;
                    count++;

                }else {
                    break;
                }

            }
            System.out.println(name+"已抢购"+count+"杯奶茶"+"店里还剩"+num+"杯奶茶");

        }

    }
}

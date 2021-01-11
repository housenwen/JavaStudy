package itheima02;

public class test02 {
    public static void main(String[] args) {
        Runnable r = new Runnable(){
            public void run(){
                for (int i = 0; i < 20; i++) {
                    System.out.println("张宇:"+i);
                }
            }
        };
        new Thread(r).start();

        for (int i = 0; i < 20; i++) {
            System.out.println("费玉清:"+i);
        }
    }
    }


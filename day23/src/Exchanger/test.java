package Exchanger;

import java.util.concurrent.Exchanger;

public class test {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new ThreadA(exchanger).start();
        new ThreadB(exchanger).start();

    }
    static class ThreadA extends Thread{

        private Exchanger<String> exchanger;

        public ThreadA(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run(){

            try {
                System.out.println("\"线程A欲传递值'礼物A'给线程B，并等待线程B的值...\"");
                System.out.println("在线程A中得到线程B的值="+exchanger.exchange("礼物A"));
            }catch (InterruptedException e){
              e.printStackTrace();
            }

        }
    }
    static class ThreadB extends Thread{
        private Exchanger<String> exchanger;

        public ThreadB(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }
        @Override
        public void  run(){
            try {
                System.out.println("\"线程B欲传递值'礼物B'给线程A，并等待线程A的值...\"");
                System.out.println("在线程B中得到线程A的值="+exchanger.exchange("礼物B"));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

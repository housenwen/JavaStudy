package Exchanger;

import java.util.concurrent.Exchanger;

public class work {

        public static void main(String[] args) {
            Exchanger<String> exchanger = new Exchanger<String>();
            ThreadA a = new ThreadA(exchanger);
            a.start();
        }
    }


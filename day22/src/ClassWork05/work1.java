package ClassWork05;

import java.util.ArrayList;

public class work1 {
    public static void main(String[] args) {

        SuiDao sd = new SuiDao();

        ArrayList<Thread>  list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new Thread(sd,i+"号"));
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).start();
        }

    }
}

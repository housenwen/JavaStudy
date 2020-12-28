package itheima05;

import java.util.ArrayList;

public class Test02 {
    public static void main(String[] args) {

        ArrayList<Avengers> list = new ArrayList<>();

        Avengers a1 = new Avengers(1,"钢铁侠","男");
        Avengers a2 = new Avengers(2,"美国队长","男");
        Avengers a3 = new Avengers(3,"黑寡妇","女");
        Avengers a4 = new Avengers(4,"绿巨人","男");
        Avengers a5 = new Avengers(5,"雷神","男");
        Avengers a6 = new Avengers(6,"星云","女");

        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
        list.add(a6);

        for (int i = 0; i < list.size(); i++) {

            Avengers avengers = list.get(i);
            String sex = avengers.getSex();
            String name = avengers.getName();
            int length =  name.length();

            if (sex.equals("男")||length>=3){

                list.remove(avengers);
                i--;
            }
        }
        for (int i = 0; i < list.size(); i++) {
          Avengers avengers =  list.get(i);

            System.out.println(avengers.getId()+avengers.getName()+avengers.getSex());

        }

    }
}

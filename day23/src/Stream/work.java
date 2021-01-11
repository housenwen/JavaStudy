package Stream;

import java.util.ArrayList;

public class work {
    public static void main(String[] args) {

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        list1.add("周杰伦");
        list1.add("张学友");
        list1.add("刘华");
        list1.add("郭富城");
        list1.add("TFBOYS");
        list1.add("王刚");
        list1.add("TFBOYS");
        list1.add("王刚");

        list2.add("李雷");
        list2.add("韩梅梅");
        list2.add("德玛嘶哑");
        list2.add("张马冬梅");
        list2.add("张夏洛");
        list2.add("张的帅");


        for (int i = 0; i < list1.size(); i++) {

            if (list1.get(i).length() != 3) {
                list1.remove(i);
            }
            if (i>3){
                list1.remove(i);
            }
        }
        System.out.println(list1);
        for (int i = 0; i < list2.size(); i++) {

            if (!list1.get(i).startsWith("张")){
                list2.remove(i);
            }
            if (i<2){
                list2.remove(i);
            }
        }
        System.out.println(list2);

        ArrayList<String> list = new ArrayList<>();

        list.addAll(list1);
        list.addAll(list2);
        System.out.println(list);
    }
}

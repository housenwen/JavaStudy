package itheima;

import java.util.ArrayList;

public class test03 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("test");
        list.add("张三");
        list.add("李四");
        list.add("test");
        list.add("test");

        String t = "test";

        for (int i = 0; i < list.size(); i++) {
          String s  =  list.get(i);
//          if (t==s){
//              list.remove(i);
//              i--;
//          }
            if (t.equals(s)){
                list.remove(i);
                i--;
            }
        }
        System.out.println(list);
    }
}

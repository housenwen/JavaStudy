package itheima03;

import java.util.LinkedList;

public class test01 {
    public static void main(String[] args) {

        LinkedList list = new LinkedList();

        list.add("周杰伦");
        list.add("周星驰");
        list.add("周华建");
        list.add("周润发");

        System.out.println(list.pop());
        String s = (String) list.pop();

        System.out.println(s);
        System.out.println(list.peekFirst());
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.removeFirst());
        System.out.println(list.remove());
        System.out.println(list.removeLast());


        System.out.println(list);


    }
}

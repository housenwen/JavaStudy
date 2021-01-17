package Proxy;

import java.util.ArrayList;

public class testP {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("周星驰");
        list.add("周润发");
        ArrayListProxy proxy = getList(list);

        for (int i = 0; i < proxy.size(); i++) {
            System.out.println(proxy.get(i));
        }
    }

    private static ArrayListProxy getList(ArrayList<String> list) {
        return new ArrayListProxy(list);
    }
}

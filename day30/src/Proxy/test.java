package Proxy;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("周星驰");
        list.add("周润发");
        List<String> proxyList = getList(list);

//        for (String s:proxyList){
//            System.out.println(s);
//        }
        for (int i = 0; i < proxyList.size(); i++) {
            System.out.println(proxyList.get(i));
        }
    }

    private static List<String> getList(List<String> list) {

        ArrayListProxy proxy = new ArrayListProxy(list);
        return proxy;

    }
}

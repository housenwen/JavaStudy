package Proxy;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        //定义一个集合对象
        ArrayList<String> list = new ArrayList<>();

        list.add("张三");
        list.add("李四");
        list.add("王五");

        //调用getList()方法，根据这个集合获取一个它的"不可变"对象
        List<String> listProxy = getList(list);
//        List<String> listProxy2 = new ArrayListProxy(list);


        for (int i = 0; i < listProxy.size(); i++) {//OK的
            System.out.println(listProxy.get(i));//OK的

        }

        //其它方法都会抛出异常
//        listProxy.add("赵六");//UnsupportedOperationException
//        listProxy.remove(0);//UnsupportedOperationException
//        listProxy.set(0, "张老三");//UnsupportedOperationException

    }
    //此方法接收一个List<String>对象，返回一个不可变的"代理对象"
    public static List<String> getList(List<String> list) {
        ArrayListProxy proxy = new ArrayListProxy(list);
        return proxy;
    }
    }


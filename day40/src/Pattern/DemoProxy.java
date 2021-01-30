package Pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//todo unmodifiableList()方法动态代理
public class DemoProxy {
    public static void main(String[] args) {
        //使用多态创建List集合,并添加元素
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        //todo 调用Collections中的方法unmodifiableList对List集合进行代理
        list = Collections.unmodifiableList(list);
//        list = Collections.unmodifiableList(list),

        //如果使用的size,get方法,没有对集合进行修改,则允许执行
        System.out.println(list.size());
        System.out.println(list.get(1));
        //todo 如果使用的add,remove,set方法,对集合进行了修改,则抛出运行时异常
        //list.add("d");//UnsupportedOperationException
        //list.remove(0);//UnsupportedOperationException
//        list.set(1, "www");//UnsupportedOperationException

    }
}

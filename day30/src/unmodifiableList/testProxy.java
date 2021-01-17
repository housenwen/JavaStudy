package unmodifiableList;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Proxy;

public class testProxy {
    //1.定义一个代理方法proxyList
    public List proxyList(List<String> list){
        //2.方法内部可以使用Proxy类中的方法实现动态代理

        List<String> pList = (List<String>) Proxy.newProxyInstance(testProxy.class.getClassLoader(),
                list.getClass().getInterfaces(),
                new InvocationHandlerImpl(list));

        return pList;

}
    @Test
    public void show(){
        //使用多态创建List集合,并添加元素
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        //调用Collections中的方法unmodifiableList对List集合进行代理
        list = proxyList(list);
        //如果使用的size,get方法,没有对集合进行修改,则允许执行
        System.out.println(list.size());
        System.out.println(list.get(1));
        //如果使用的add,remove,set方法,对集合进行了修改,则抛出运行时异常
        //list.add("d");//UnsupportedOperationException
        //list.remove(0);//UnsupportedOperationException
        //list.set(1, "www");//UnsupportedOperationException
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}

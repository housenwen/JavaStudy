package Proxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CollectionProxyTest {

    public static void main(String[] args) {
        //将集合转成代理对象
        Collection<String> collection = new CollectionProxy().proxyCollection(new ArrayList<String>());
        //批量添加元素做测试
        Collections.addAll(collection,"abc","aaa","bbb","abc","ccc","abc");
        //调用代理之后的remove方法
        collection.remove("abc");
        //检查执行效果
        System.out.println(collection);
    }

}


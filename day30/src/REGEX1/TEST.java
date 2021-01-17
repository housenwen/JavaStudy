package REGEX1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class TEST {
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
class CollectionProxy {

    //1.定义一个代理方法proxyCollection
    public Collection proxyCollection(Collection<String> collection){
        //2.方法内部可以使用Proxy类中的方法实现动态代理
        Collection<String> pCollection = (Collection<String>) Proxy.newProxyInstance(collection.getClass().getClassLoader(),
                collection.getClass().getInterfaces(),
                new InvocationHandlerImpl(collection));
        return pCollection;
    }

}
class InvocationHandlerImpl implements InvocationHandler {
    //定义一个Collection集合变量
    private Collection<String> collection;

    //给Collection集合赋值
    public InvocationHandlerImpl(Collection<String> collection) {
        super();
        this.collection = collection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //触发方法
        Object v = method.invoke(collection,args);
        //invoke方法会获取collection集合的方法,在invoke方法内部对collection集合的方法进行拦截
        //获取collection集合方法的名字
        String collectionMethodName = method.getName();
        //对获取的名字进行判断
        //如果使用的remove方法,能够删除集合中所有匹配的元素
        if("remove".equals(collectionMethodName)){
            //获取集合的迭代器
            Iterator<String> it = collection.iterator();
            //遍历删除
            while(it.hasNext()){
                String x = it.next();
                if(x.equals("abc")){
                    it.remove();
                }
            }
        }
        return v;
    }
}
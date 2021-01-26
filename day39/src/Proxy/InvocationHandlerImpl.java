package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

public class InvocationHandlerImpl implements InvocationHandler {
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



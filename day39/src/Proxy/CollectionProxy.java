package Proxy;
import java.lang.reflect.Proxy;
import java.util.Collection;

public class CollectionProxy {
    //1.定义一个代理方法proxyCollection
    public Collection proxyCollection(Collection<String> collection){
//        2.方法内部可以使用Proxy类中的方法实现动态代理
        Collection<String> pCollection = (Collection<String>)
        Proxy.newProxyInstance(collection.getClass().getClassLoader(),
                collection.getClass().getInterfaces(),
                new InvocationHandlerImpl(collection));
        return pCollection;
    }
}


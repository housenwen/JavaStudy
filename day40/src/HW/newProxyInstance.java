package HW;

import com.sun.corba.se.impl.presentation.rmi.InvocationHandlerFactoryImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 需求:
 *
 * 	模拟unmodifiableList方法,对List接口进行代理
 * 		调用List接口的方法会被拦截
 * 		如果使用的size,get方法,没有对集合进行修改,则允许执行
 * 		如果使用的add,remove,set方法,对集合进行了修改,则抛出运行时异常
 * 分析:
 * 	1.定义一个代理方法proxyList
 * 		参数:传递List集合
 * 		返回值:被代理之后的List集合
 * 	2.方法内部可以使用Proxy类中的方法实现动态代理
 */
public class newProxyInstance {
    public static void main(String[] args) {
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
//        list.add("d");//UnsupportedOperationException
//        list.remove(0);//UnsupportedOperationException
//        list.set(1, "www");//UnsupportedOperationException
    }


    //1.定义一个代理方法proxyList
    public static List proxyList(List<String> list){
        List<String> pList =
                (List<String>) Proxy.newProxyInstance(newProxyInstance.class.getClassLoader(),
                        list.getClass().getInterfaces(),new InvocationHandlerImpl(list));
        return pList;
    }
}

class InvocationHandlerImpl implements InvocationHandler {
    //定义一个List集合变量
    private List<String> list;

    //给List集合赋值
    public InvocationHandlerImpl(List<String> list) {
        super();
        this.list = list;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        //invoke方法会获取List集合的方法,在invoke方法内部对List集合的方法进行拦截
        //获取List集合方法的名字
        String listMethodName = method.getName();
        //对获取的名字进行判断
        //如果使用的add,remove,set方法,对集合进行了修改,则抛出运行时异常
        if("add".equals(listMethodName)){
            throw new UnsupportedOperationException("add no run");
        }
        if("remove".equals(listMethodName)){
            throw new UnsupportedOperationException("remove no run");
        }
        if("set".equals(listMethodName)){
            throw new UnsupportedOperationException("set no run");
        }
        //如果使用的size,get方法,没有对集合进行修改,则允许执行
        Object v = method.invoke(list,args);
        return v;

    }
}



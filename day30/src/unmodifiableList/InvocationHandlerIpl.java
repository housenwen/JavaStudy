package unmodifiableList;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

class InvocationHandlerImpl implements InvocationHandler {
    //定义一个List集合变量
    private List<String> list;

    //给List集合赋值
    public InvocationHandlerImpl(List<String> list) {
        super();
        this.list = list;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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

package Login;


public class MyException extends Exception{
    public MyException(){
    }
    public MyException(String message){
        super(message);
    }
}
/**以下描述正确的是:(   )

 A:  如果某个方法抛出了这个异常,那么在使用这个方法的时候必须try-catch处理

 B:  如果某个方法抛出了这个异常,那么在使用这个方法的时候必须throws处理

 C:  如果某个方法抛出了这个异常,那么在使用这个方法的时候可以不用处理

 D:  如果某个方法抛出了这个异常我们可以用方式或者方式处理

 答案：D {为啥不是A？}

 */

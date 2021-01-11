package ClassWork;
/**请按以下步骤要求编写代码：

 Ø  定义一个“年龄异常类”：AgeException，使其继承自RuntimeException，并添加无参、String参数的构造方法；

 Ø  定义一个“性别异常类”：SexException，使其继承自RuntimeException，并添加无参、String参数的构造方法；

 Ø  定义一个Student类，属性：姓名、性别、年龄。

 在性别的set方法中判断是否是男/女，如果不是则抛出：性别异常

 在年龄的set方法中判断年龄是否是15--50之间，如果不是则抛出：年龄异常

 Ø  编写测试类，创建一个Student对象，并在try{}中调用setXxx()方法为对象属性赋值，在catch()中打印年龄错误。

 */

public class TestStudent {
    public static void main(String[] args) {
        Student student = new Student();
        try {
            student.setAge(80000);
        }catch (RuntimeException e){
            System.out.println(e );
        }
        try{
            student.setName("张三");
        }
        catch (RuntimeException e){
            System.out.println("姓名过长");
        }finally {
//            student.setName("8888*");
        }

        try {
            student.setSex("赵四");
        }catch (RuntimeException e){
            System.out.println(e);
        }
        System.out.println(student);
    }
}

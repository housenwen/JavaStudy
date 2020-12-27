package itheima05;



//4、现已知工人（Worker）类，属性包含姓名（name）、工龄（year），请编写该类，提供构造方法和get、set方法。
// 在测试类中，请查看键盘录入Scanner类的API，创建工人类对象，属性值由键盘录入，打印对象的属性值。输出效果如下：
//	请录入工人姓名：
//	张三
//	请录入工人工龄：
//	5
//	该工人对象的属性信息如下：
//	姓名：张三
//	工龄：5
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Worker worker = new Worker();

        System.out.println("请录入工人姓名：");
        String name = scanner.nextLine();
        worker.setName(name);

        System.out.println("请录入工人工龄：");
        int age = scanner.nextInt();
        worker.setAge(age);
        System.out.println("该工人对象的属性信息如下：");
        System.out.println("姓名："+worker.getName());
        System.out.println("工龄："+worker.getAge());

    }
}

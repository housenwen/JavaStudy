package itheima01;

public class test {
    public static void main(String[] args) {
        // 创建抽象类,抽象类不能创建对象
        // 假设抽象类让我们创建对象,里面的抽象方法没有方法体,无法执行.所以不让我们创建对象
//		Employee e = new Employee();
//		e.work();

        // 3.创建子类
        Manager m = new Manager();
        m.work();

//        Cook c = new Cook("ap002", "库克", 1);
        Cook c = new Cook();
        c.work();


    }
}

package itheima;

public class TestWrok {
    public static void main(String[] args) {
        Manager manager = new Manager();
//        manager.work();
        manager.setId(123);
        manager.setName("项目经理");
        manager.setSalary(15000);
        manager.setBonus(6000);
        manager.work();

        Coder coder = new Coder();
        coder.setId(135);
        coder.setName("程序员");
        coder.setSalary(10000);
        coder.work();

    }
}

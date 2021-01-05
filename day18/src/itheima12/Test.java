package itheima12;

public class Test {
    public static void main(String[] args) {

        Clerk clerk = new Clerk("李小亮","C001","销售部","张小强");
        Manager manager = new Manager("张小强","M001","销售部","李小亮");

        manager.showMsg();
        clerk.showMsg();

    }
}

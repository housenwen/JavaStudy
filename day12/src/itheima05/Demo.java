package itheima05;

public class Demo {

    public static void main(String[] args) {
        //员工
        Employee employee = new Employee();
        employee.notice("工作日报");

        //经理
        Manager manager = new Manager();
        manager.notice("会议内容");
    }
}

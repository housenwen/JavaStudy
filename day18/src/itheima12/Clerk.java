package itheima12;

public class Clerk extends Employee{
    private String manager ;

    public Clerk() {
    }

    public Clerk(String manager) {
        this.manager = manager;
    }

    public Clerk(String name, String workId, String dept, String manager) {
        super(name, workId, dept);
        this.manager = manager;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public void showMsg() {
        System.out.println("销售部的："+this.getName()+",员工编号："+this.getWorkId()+"他的经理是"+this.getManager());
    }
}

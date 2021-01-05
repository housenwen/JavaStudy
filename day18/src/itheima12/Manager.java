package itheima12;

public class Manager extends Employee{
    private String clerk;

    public Manager(String clerk) {
        this.clerk = clerk;
    }

    public Manager(String name, String workId, String dept, String clerk) {
        super(name, workId, dept);
        this.clerk = clerk;
    }

    public Manager() {
    }

    public String getClerk() {
        return clerk;
    }

    public void setClerk(String clerk) {
        this.clerk = clerk;
    }

    @Override
    public void showMsg() {
        System.out.println("销售部的："+this.getName()+",员工编号："+this.getWorkId()+"他的职员是"+this.getClerk());
    }
}

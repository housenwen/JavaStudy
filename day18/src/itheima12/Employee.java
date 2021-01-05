package itheima12;

public abstract class Employee {
    private String name;
    private String workId;
    private String dept;

    public Employee() {
    }

    public Employee(String name, String workId, String dept) {
        this.name = name;
        this.workId = workId;
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public abstract void showMsg();
}

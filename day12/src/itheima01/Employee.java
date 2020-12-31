package itheima01;

abstract class Employee {
    private String id;
    private String name;
    private double salary;

    public Employee() {
    }

    public Employee(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // 抽象方法
    // 抽象方法必须要放在抽象类中
    abstract public void work();
}

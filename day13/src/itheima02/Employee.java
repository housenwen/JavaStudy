package itheima02;

public class Employee {
    private String name;
    private double GongZi;

    public Employee() {

    }

    public Employee(String name, double gongZi) {
        this.name = name;
        GongZi = gongZi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGongZi() {
        return GongZi;
    }

    public void setGongZi(double gongZi) {
        GongZi = gongZi;
    }
}

package itheima05;

public class Manager extends Member {

    private double bonus;

    @Override
    public void setContent(String content) {
        System.out.println(content);
    }

    public Manager() {
    }

    public Manager(double bonus) {
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}

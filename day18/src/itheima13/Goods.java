package itheima13;

public abstract class Goods {
    private String color;
    private double weight;

    public Goods() {
    }

    public Goods(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public abstract void eat();
    public abstract void uer();


}

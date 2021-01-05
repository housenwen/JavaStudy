package itheima10;

public class WashingMachine extends Electric{
    private String type;
    private double capacity;//容量

    public WashingMachine() {
    }

    public WashingMachine(String type, double capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public WashingMachine(String brand, String model, String color, double price, String type, double capacity) {
        super(brand, model, color, price);
        this.type = type;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}

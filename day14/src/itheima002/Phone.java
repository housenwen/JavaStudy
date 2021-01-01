package itheima002;

public class Phone {

    private String bound;
    private String color;
    private double price;

    public Phone() {
    }

    public Phone(String bound, String color, double price) {
        this.bound = bound;
        this.color = color;
        this.price = price;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "bound='" + bound + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }

    public void show(){

        System.out.println(this.getBound()+this.getColor()+this.getPrice());

    }

}

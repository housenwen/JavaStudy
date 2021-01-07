package ZuChe;

public abstract class Motovehicle {
    private String brand;//品牌
    private String type;//车牌
    private double price;//日租金

    public Motovehicle() {
    }

    public Motovehicle(String brand, String type, double price) {
        this.brand = brand;
        this.type = type;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Motovehicle{" +
                "brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

    public abstract void YouHui();

}

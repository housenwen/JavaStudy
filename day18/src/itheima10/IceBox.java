package itheima10;

public class IceBox extends Electric{
    private String type;
    private String refrigeration;//制冷方式

    public IceBox() {
    }

    public IceBox(String type, String refrigeration) {
        this.type = type;
        this.refrigeration = refrigeration;
    }

    public IceBox(String brand, String model, String color, double price, String type, String refrigeration) {
        super(brand, model, color, price);
        this.type = type;
        this.refrigeration = refrigeration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRefrigeration() {
        return refrigeration;
    }

    public void setRefrigeration(String refrigeration) {
        this.refrigeration = refrigeration;
    }
}

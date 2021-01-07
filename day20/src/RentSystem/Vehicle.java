package RentSystem;

import java.util.Objects;

public abstract class  Vehicle {
    private String price;//租金
    private String brand;//品牌
    private String id;//车牌

    public Vehicle() {
    }

    public Vehicle(String price, String brand, String id) {
        this.price = price;
        this.brand = brand;
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //车牌ID如果一样，认为是相同的车
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle that = (Vehicle) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     *
     * @param days 天数
     * @return 租金
     */
    public abstract double calRent(int days);
}

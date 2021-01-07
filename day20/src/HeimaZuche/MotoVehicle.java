package HeimaZuche;

import java.util.Objects;

public abstract class MotoVehicle {
    private String rent;//租金
    private String brand;//品牌
    private String id;//车牌

    public MotoVehicle() {
    }

    public MotoVehicle(String rent, String brand, String id) {
        this.rent = rent;
        this.brand = brand;
        this.id = id;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
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
        MotoVehicle that = (MotoVehicle) o;
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
    public abstract float calRent(int days);
}

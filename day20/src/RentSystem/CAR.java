package RentSystem;

import java.util.Objects;

public class CAR extends Vehicle {
    private String type;//车型
    public final String showStr = "租车超过7天九折优惠，超过30天八折优惠，超过150天五折优惠。";

    public CAR(String type) {
        this.type = type;
    }

    public CAR(String rent, String brand, String id, String type) {
        super(rent, brand, id);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public double calRent(int days) {
//折扣优惠:租车超过7天九折优惠，超过30天八折优惠，超过150天五折优惠。
        double price = Double.parseDouble(this.getPrice());
        double money = days * price;

        if (days < 0) {
            throw new RuntimeException("出错");

        } else if (days >= 0 && days < 7) {
            return money;
        } else if (days >= 7 && days < 30) {
            return money * 0.9;
        } else if (days >= 30 && days < 150) {
            return money * 0.8;
        } else {
            return money * 0.5;
        }

    }

    @Override
    public String toString() {
        return getBrand() +
                " " + getType() +",车牌号为：" + getId() +
                ",租金每天" + getPrice() + "元, " + showStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CAR car = (CAR) o;
        return type.equals(car.type) &&
                showStr.equals(car.showStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, showStr);
    }
}

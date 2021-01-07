package RentSystem;

import java.util.Objects;

public class BUS extends Vehicle{

    private int seats;
    private final String discountMsg = "租车超过3天九折，超过7天八折，超过30天七折，超过150天五折";

    public BUS(int seats) {
        this.seats = seats;
    }

    public BUS(String rent, String brand, String id, int seats) {
        super(rent, brand, id);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BUS bus = (BUS) o;
        return seats == bus.seats &&
                Objects.equals(discountMsg, bus.discountMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), seats, discountMsg);
    }

    @Override
    public double calRent(int days) {

        double price = Double.parseDouble(getPrice());
        double money = days * price;

        if (days < 0) {
            throw new RuntimeException("出错");

        } else if (days >= 0 && days < 3) {
            return money;
        } else if (days >= 3 && days < 7) {
            return money*0.9;
        } else if (days >= 7 && days < 30) {
            return money * 0.8;
        } else if (days >= 30 && days < 150) {
            return money * 0.7;
        } else {
            return money * 0.5;
        }
    }
    @Override
    public String toString() {
        return getBrand() + getSeats() + "座" +
                ",车牌号为：" + getId() +
                ",租金每天" + getPrice() + "元," + discountMsg;

    }
}

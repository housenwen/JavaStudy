package HeimaZuche;

public class Bus extends MotoVehicle{
    private int seats;
    private final String discountMsg = "租车超过3天九折，超过7天八折，超过30天七折，超过150天五折";

    public Bus(int seats) {
        this.seats = seats;
    }

    public Bus(String rent, String brand, String id, int seats) {
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
    public float calRent(int days) {
        int money = Integer.parseInt(getRent()) * days;
        if (days < 3) {
            return money;
        } else if (days < 7) {
            return money * 0.9f;
        } else if (days < 30) {
            return money * 0.8f;
        } else if (days < 150) {
            return money * 0.7f;
        } else {
            return money * 0.5f;
        }
    }

    @Override
    public String toString() {
        return getBrand() + getSeats() + "座" +
                ",车牌号为：" + getId() +
                ",租金每天" + getRent() + "元," + discountMsg;

    }
}


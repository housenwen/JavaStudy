package HeimaZuche;


public class Car extends MotoVehicle {
    private String type;//车型
    public final String discountMsg = "租车超过7天九折优惠，超过30天八折优惠，超过150天五折优惠。";

    public Car(String type) {
        this.type = type;
    }

    public Car(String rent, String brand, String id, String type) {
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
    public float calRent(int days) {
        //折扣优惠:租车超过7天九折优惠，超过30天八折优惠，超过150天五折优惠。
        int money = Integer.parseInt(getRent()) * days;
        if (days < 0) {
            throw new RuntimeException("出错");
        } else if (days <= 7) {
            return money;
        } else if (days <= 30) {
            return money * 0.9f;
        } else if (days <= 150) {
            return money * 0.8f;
        } else {
            return money * 0.5f;
        }

    }

    @Override
    public String toString() {
        return getBrand() + " " + getType() +
                ",车牌号为：" + getId() +
                ",租金每天" + getRent() + "元, " + discountMsg;
    }
}

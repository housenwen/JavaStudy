package ZuChe;

public class Car extends Motovehicle{


    public Car() {
    }

    public Car(String brand, String type, double price) {
        super(brand, type, price);
    }


    @Override
    public void YouHui() {
        System.out.println("优惠活动：租车超过7天九折优惠，超过30天八折优惠，超过150天五折优惠。");
    }
}

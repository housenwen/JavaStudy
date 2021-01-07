package ZuChe;

public class Bus extends Motovehicle{



    public Bus(String brand, String type, double price) {
        super(brand, type, price);
    }


    @Override
    public void YouHui() {
        System.out.println("优惠活动：租车超过3天九折，超过7天八折，超过30天七折，超过150天五折。");
    }
}

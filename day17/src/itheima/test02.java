package itheima;

public class test02 {
    public static void main(String[] args) {

        Person p = new Person();

        p.setAge(12);
        p.setName("Make");
        IDCard idCard = new IDCard("4567834567567","上海市浦东新区");

        p.setIdCard(idCard);

        System.out.println(p);

    }
}

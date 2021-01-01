package itheima03;

public class Test {

    public static void main(String[] args) {

        //11. 在main方法中创建GouWuChe对象gouWuChe
        Gouwuche gouWuChe = new Gouwuche();
        //12. 在main方法中创建商品Laptop,名称为:笔记本,id为:g10000,价格为:10000
        Shangpin g1 = new Bijiben("笔记本", "g10000", 10000);
        //13. 在main方法中创建商品Phone,名称为:手机,id为:g10001,价格为:5000
        Shangpin g2 = new Shouji("手机", "g10001", 5000);
        //14. 在main方法中创建商品Fruit,名称为:苹果,id为:g20000,价格为:50
        Shangpin g3 = new PingGuo("苹果", "g20000", 50);

        System.out.println("============添加商品=================");
        //15. 调用购物车的addGoods方法将3个商品添加到购物车中
        gouWuChe.tianjia(g1);
        gouWuChe.tianjia(g2);
        gouWuChe.tianjia(g3);

        System.out.println("============打印商品=================");
        //16. 调用购物车的showGoods方法,显示购物车中的商品信息
        gouWuChe.dayin();
        //17. 调用购物车的total方法,显示购物车中所有商品的价格
        gouWuChe.Jisuan();

    }

}

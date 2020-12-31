package itheima05;
//2.1 题目：
//	我们计划为一个电器销售公司制作一套系统，公司的主要业务是销售一些家用电器，例如：电冰箱、洗衣机、电视机产品。
//	类的设计为：
//冰箱类
//属性：品牌、型号、颜色、售价、门款式、制冷方式
//洗衣机类
//属性：品牌、型号、颜色、售价、电机类型、洗涤容量
//电视类
//属性：品牌、型号、颜色、售价、屏幕尺寸、分辨率
public class JiaDian {

    private String pinpai;
    private String xinghao;
    private String color;
    private double price;

    public JiaDian() {
    }

    public JiaDian(String pinpai, String xinghao, String color, double price) {
        this.pinpai = pinpai;
        this.xinghao = xinghao;
        this.color = color;
        this.price = price;
    }

    public String getPinpai() {
        return pinpai;
    }

    public void setPinpai(String pinpai) {
        this.pinpai = pinpai;
    }

    public String getXinghao() {
        return xinghao;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

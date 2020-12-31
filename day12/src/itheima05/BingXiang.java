package itheima05;

public class BingXiang extends JiaDian{
    private String kuanshi;
    private String zhileng;

    public BingXiang() {
    }

//    public BingXiang(String kuanshi, String zhileng) {
//        this.kuanshi = kuanshi;
//        this.zhileng = zhileng;
//    }

    public BingXiang(String pinpai, String xinghao, String color, double price, String kuanshi, String zhileng) {
        super(pinpai, xinghao, color, price);
        this.kuanshi = kuanshi;
        this.zhileng = zhileng;
    }

    public String getKuanshi() {
        return kuanshi;
    }

    public void setKuanshi(String kuanshi) {
        this.kuanshi = kuanshi;
    }

    public String getZhileng() {
        return zhileng;
    }

    public void setZhileng(String zhileng) {
        this.zhileng = zhileng;
    }
}

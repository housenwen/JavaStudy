package itheima05;

public class TV extends JiaDian{
    private String pingmu;
    private String fenbianlv;

    public TV() {
    }

    public TV(String pinpai, String xinghao, String color, double price, String pingmu, String fenbianlv) {
        super(pinpai, xinghao, color, price);
        this.pingmu = pingmu;
        this.fenbianlv = fenbianlv;
    }

    public String getPingmu() {
        return pingmu;
    }

    public void setPingmu(String pingmu) {
        this.pingmu = pingmu;
    }

    public String getFenbianlv() {
        return fenbianlv;
    }

    public void setFenbianlv(String fenbianlv) {
        this.fenbianlv = fenbianlv;
    }
}

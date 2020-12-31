package itheima05;

public class Xiyiji extends JiaDian {
    private String dianji;
    private String xidi;

    public Xiyiji() {
    }

//    public Xiyiji(String dianji, String xidi) {
//        this.dianji = dianji;
//        this.xidi = xidi;
//    }


    public Xiyiji(String pinpai, String xinghao, String color, double price, String dianji, String xidi) {
        super(pinpai, xinghao, color, price);
        this.dianji = dianji;
        this.xidi = xidi;
    }

    public String getDianji() {
        return dianji;
    }

    public void setDianji(String dianji) {
        this.dianji = dianji;
    }

    public String getXidi() {
        return xidi;
    }

    public void setXidi(String xidi) {
        this.xidi = xidi;
    }
}

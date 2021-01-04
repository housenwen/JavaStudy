package itheima;

public class IDCard {
   private String idNum;
   private String authority;

    public IDCard() {
    }

    public IDCard(String idNum, String authority) {
        this.idNum = idNum;
        this.authority = authority;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "IDCard{" +
                "idNum='" + idNum + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}

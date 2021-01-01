package itheima03;

public class Shangpin {

    private String bianhao;
    private String name;
    private double price;

    public Shangpin() {
    }

    public Shangpin(String bianhao, String name, double price) {
        this.bianhao = bianhao;
        this.name = name;
        this.price = price;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

package itheima01;

public class JiDong {

    private String breed;
    private String price;

    public JiDong() {
    }

    public JiDong(String breed, String price) {
        this.breed = breed;
        this.price = price;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void work(){

        System.out.println("我启动点火，跑的贼快！！！");
    }

}

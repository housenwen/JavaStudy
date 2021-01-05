package itheima10;

public class Television extends Electric{
    private String screen;//屏幕
    private String resolving;//分辨率

    public Television() {
    }

    public Television(String screen, String resolving) {
        this.screen = screen;
        this.resolving = resolving;
    }

    public Television(String brand, String model, String color, double price, String screen, String resolving) {
        super(brand, model, color, price);
        this.screen = screen;
        this.resolving = resolving;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getResolving() {
        return resolving;
    }

    public void setResolving(String resolving) {
        this.resolving = resolving;
    }
}

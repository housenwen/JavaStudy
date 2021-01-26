package Singleton;

public class King {

    private String name;

    private static King instance = new King("成吉思汗");

//    private King(){}

    public King(String name) {
        this.name = name;
    }

    public static King getInstance(){
        return instance;
    }

    @Override
    public String toString() {
        return "King{" +
                "name='" + name + '\'' +
                '}';
    }
}

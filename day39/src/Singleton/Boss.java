package Singleton;

public class Boss {

    private String name;

    private static Boss instance;

    public Boss(String name) {
        this.name = name;
    }

    public static Boss getInstance(){
        if (instance==null){
            instance=new Boss("马云");
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "name='" + name + '\'' +
                '}';
    }
}

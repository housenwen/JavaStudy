package HK;

public class SingleDome {
    private static SingleDome instance = new SingleDome();
    private SingleDome(){

    }
    public static SingleDome getInstance(){
        return instance;
    }
}

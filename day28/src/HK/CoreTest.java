package HK;

public class CoreTest implements Runnable{

    private String id;

    public CoreTest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CoreTest(String id) {
        this.id = id;
    }

    @Override
    public void run() {

    }
}

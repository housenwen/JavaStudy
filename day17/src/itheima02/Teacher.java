package itheima02;

public class Teacher extends Person{

    public Teacher() {
    }

    public Teacher(int id, String name, String sex, String birthday) {
        super(id, name, sex, birthday);
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String geyWork() {
        return null;
    }
}

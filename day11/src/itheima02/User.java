package itheima02;

public class User {

    private String name;
    private String password;
    private String birthday;
    private String hob;

    public User() {
    }

    public User(String name, String password, String birthday, String hob) {
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.hob = hob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHob() {
        return hob;
    }

    public void setHob(String hob) {
        this.hob = hob;
    }
}


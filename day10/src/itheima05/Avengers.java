package itheima05;
//1定义一个Avengers类
//
//  	 1)属性: 编号id ( int类型 ) ，姓名name（String类型），性别sex （String类型）
//
//  	2)方法：空参满参构造 set、get方法   
public class Avengers {
    private int id;
    private String name;
    private String sex;

    public Avengers() {
    }

    public Avengers(int id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

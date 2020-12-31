package Itheima03;

/*
 *
创建Student类，有如下属性：
学号、姓名、性别、年龄
全部属性使用String类型。要求有无参，全参构造方法。所有属性私有，并提供公有get/set方法。
 */
public class Student {
    private String id;//学号
    private String name;//名字
    private String sex;// 性别
    private String age;//年龄
    public Student() {
//        super();
    }
    public Student(String id, String name, String sex, String age) {
//        super();
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
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
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }


}

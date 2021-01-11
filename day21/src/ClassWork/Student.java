package ClassWork;

public class Student {
    private String name;
    private String sex;
    private int age;

    public Student() {
    }

    public Student(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length()>5){
            throw new RuntimeException("姓名太长了");
        }
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
//        try {
//            this.sex = sex;
//        }catch (SexException e){
//            System.out.println(e+"性别异常");
//        }
        if (!sex.equals("男")&&!sex.equals("女")){
            throw new SexException("性别异常");
        }

        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

//        try {
//            this.age = age;
//        }catch (AgeException e){
//            System.out.println(e+"年龄异常");
//        }
        if (age>15||age<50){

            throw new AgeException("年龄异常");
        }
        this.age= age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}

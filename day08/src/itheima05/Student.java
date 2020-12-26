package itheima05;
/*
JavaBean类：封装数据
*/
public class Student {
    private String name;
    private int age;
//    无参数构造
    public Student(){

    }
//    有参数构造
    public Student(String name,int age){
        this.name = name;
        this.age = age;
    }
//    setXxx和getXxx
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }

    public void show(){
        System.out.println(name+"..."+age);
    }

}

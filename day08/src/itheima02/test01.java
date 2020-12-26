package itheima02;

public class test01 {

    public class Student{
        String name;
        int age;
        public void study(){
            System.out.println("好好学习,天天向上");
        }
    }
    public class TestStudent{

        public  void main(String[] args) {

            Student student = new Student();
            System.out.println(student);

        }
    }

}

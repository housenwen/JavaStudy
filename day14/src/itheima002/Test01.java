package itheima002;

public class Test01 {

    public static void main(String[] args) {
        Student s1 = new Student("张三","男",22);
        Student s2 = new Student("李四","男",23);


        boolean boo  =   s1.equals(s2);

        System.out.println(boo);

        System.out.println(s1.equals(s2));


    }



}

package itheima02;

import java.util.ArrayList;

public class text01 {
    public static void main(String[] args) {
        //3.在main()方法中，创建一个可以存储Student对象的集合对象；
        ArrayList<Student> list = new ArrayList<Student>();
        //4.创建以下几个Student对象：
        //5.将上面几个对象添加到集合中
        list.add(new Student("it001","黄渤", 1.72));
        list.add(new Student("it002","孙红雷", 1.78));
        list.add(new Student("it003","章子怡", 1.64));
        list.add(new Student("it004","杨颖", 1.68));
        //6.遍历集合打印每个对象的属性值；
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            System.out.println(s.getId()+","+s.getName()+","+s.getHeight());
        }
        //7.获取并打印集合大小；
        int size = list.size();
        System.out.println("打印集合大小:" + size);
        //8.获取第3个学员对象，并打印其属性值；
        Student stu = list.get(2);
        System.out.println("第3个学员对象:"+stu.getId()+","+stu.getName()+","+stu.getHeight());
        //9.移除掉第4个学员对象；
        list.remove(3);
        //10.再次获取并打印集合大小；
        size = list.size();
        System.out.println("打印集合大小:" + size);
        //	11.	将第一个对象替换为以下对象： it005	撒贝宁	1.67
        Student student = new Student("it0005", "撒贝宁", 1.67);
        list.set(0, student);
        //12.遍历集合，打印出身高在1.70以上的学员信息；
        //13.遍历集合，打印出“孙”姓的学员信息；
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            //打印出身高在1.70以上的学员信息
            if (s.getHeight() > 1.70) {
                System.out.println("身高在1.70以上的学员信息:"+s.getId()+","+s.getName()+","+s.getHeight());
            }
            //打印出“孙”姓的学员信息；
            if (s.getName().startsWith("孙")) {
                System.out.println("“孙”姓的学员信息:"+s.getId()+","+s.getName()+","+s.getHeight());
            }
        }
    }
}

package itheima01;

import java.util.ArrayList;

public class test08 {
    public static void main(String[] args) {
//        ArrayList<int> al = new ArrayList<int>();
//        al.add(11);
//        al.add(22);
//        System.out.println(al.get(al.size()-1));
//        ArrayList<Student> list = new ArrayList<Student>();
//        list.add(new Student("琳琳",18));
//        list.add(new Student("丽丽",19));
//        for (int i = 0; i < list.size(); i++) {
//     if(list.get(i).getAge() > 18){
// System.out.println(list.get(i).getName());
//}
//        ArrayList<Student> list = new ArrayList<Student>();
//        list.add(new Student("琳琳",18));
////        list.add(new Teacher("Tom",22));
//        for (int i = 0; i < list.size(); i++) {
// System.out.println(list.get(i).getName());
//        }

        ArrayList<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("bb");
        print(list); //调用方法
    }
        public static void print(ArrayList<String> list){
for (int i = 0; i < list.size(); i++) {
System.out.println(list.get(i));
 }
        }
}

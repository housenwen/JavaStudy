package itheima02;

import java.util.ArrayList;

public class test01 {
    public static void main(String[] args){
        /*
			假如有两个班的学生姓名，它们分别存储在两个集合中：
		*/
        //第一个班
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("迪丽热巴");
        list1.add("古力娜扎");
        list1.add("柳岩");
        list1.add("杨幂");

        //第二个班
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("蔡徐坤");
        list2.add("杨坤");
        list2.add("陈伟霆");
        list2.add("李易峰");

        //将两个集合存储到一个集合中
        ArrayList<ArrayList<String>> allList = new ArrayList<>();
        allList.add(list1);
        allList.add(list2);

        //遍历allList，取出每个ArrayList
        for(ArrayList<String> list : allList){
            //遍历每个班的ArrayList
            for(String s : list){
                System.out.println(s);
            }
        }
    }
}

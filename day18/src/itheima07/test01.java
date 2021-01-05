package itheima07;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.ArrayList;
import java.util.Collections;


//1. 有如下一个数据操作接口IDataOperation<E>和一个学生类Student，
// 现需要提供一个IDataOperation接口的实现类，用来对学生信息进行操作；
//2. 在IDataOperation接口实现类中，使用集合保存学生信息；
//3. 注意：学生信息包括学号、姓名、年龄和地址；
//其中，学生的学号是唯一的，不能出现两个学号一样的学生。
public class test01 {
    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<>();
        
        Student s1 = new Student("001","周杰伦","29","台湾省");
        Student s2= new Student("002","王杰","21","河北省");
        Student s3 = new Student("003","毕福剑","22","河南省");
        Student s4 = new Student("004","林俊杰","21","安徽省");
        Student s5 = new Student("005","谢霆锋","25","山西省");
        Student s6 = new Student("006","刘德华","21","江苏省");


        Collections.addAll(list,s1,s2,s3,s4,s5);
        
//        list.getAll();
       IDataOperationImpl iData = new IDataOperationImpl(list);

//       iData.getAll();
       iData.add(s1);
       iData.add(s6);

       iData.delete("002");

       iData.update(s1);

        System.out.println( iData.get("周杰伦"));

        System.out.println(iData.getAll());






        

    }
}

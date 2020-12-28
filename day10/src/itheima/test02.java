package itheima;

import com.sun.deploy.security.CredentialManager;

import java.util.ArrayList;
import java.util.Arrays;

public class test02 {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        list.add("test");
        list.add("张三");
        list.add("李四");
        list.add("test");
        list.add("test");
//        根据元素进行删除，只会删除第一个相同元素
        list.remove("test");
        list.remove("test");
        list.remove("test");

        System.out.println(list);

    }
}

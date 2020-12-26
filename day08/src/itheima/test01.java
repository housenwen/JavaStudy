package itheima;

public class test01 {
    public static void main(String[] args) {
//        选择要设置断点的代码行，在行号的区域后面单击鼠标左键即可。
        int i = 10;
        int j = 20;
//        int num = i+j;
//
//        System.out.println("和为："+num);
        i = i^j;
        System.out.println(i);
        j = j^i;
        System.out.println(i);
        System.out.println(j);
        i = i^j;
        System.out.println(i);

    }
}

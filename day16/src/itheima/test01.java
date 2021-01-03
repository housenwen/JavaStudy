package itheima;

public class test01 {
    public static void main(String[] args) {

        char c = 'a';
        String s = "123";
        s+=c;
        System.out.println(s);

        System.out.println("---------------");

        if (c>='a'&&c<='z'||c>='A'&&c<='Z'){
            s+=c;
            System.out.println(s);
        }


    }
}

package itheima01;

public class test03 {
    public static void main(String[] args) {
        String s = "12345";
        String s2 = "";
        for (int i = s.length()-1; i >= 0; i--) {
            s2+= s.charAt(i);}
        s = s2;
        System.out.println(s);
    }
}

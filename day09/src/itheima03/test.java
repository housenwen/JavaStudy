package itheima03;

public class test {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1==s2);

        boolean b1 = s1.equals(s2);
        System.out.println(b1);

        String s3 = "ABC";

        boolean b2 = s1.equalsIgnoreCase(s3);

        System.out.println(b2);

        int a1 = s3.length();

        System.out.println(a1);

        for (int i = 0; i < s3.length(); i++) {
            System.out.println(s3.charAt(i));
        }
        char a2 = s3.charAt(1);
        System.out.println(a2);

        char [] chars = s1.toCharArray();
        System.out.println(chars);

        String s5 = s3.substring(1);
        char [] chars1 = s5.toCharArray();
        System.out.println(chars1);

        String s6 = s1.substring(0,2);
        System.out.println(s6);

     String s7 =  s3.replace("ABC","123");
     char [] chars2 = s7.toCharArray();
        System.out.println(chars2);

        String[] p = s1.split("a");
        String t =  p.toString();
        System.out.println(t);

    }

}

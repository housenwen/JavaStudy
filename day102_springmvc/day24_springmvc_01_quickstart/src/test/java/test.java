public class test {

    public static void main(String[] args) {
        int a = 1;
        //00000001
        int b = 2;
        //00000010
        int i = a ^ b;
        System.out.println(i);
        int j = i ^ a;
        //i=00000011
        //a=00000001
        //00000000
        //00000001
        System.out.println(j);
        int c = 10;
        int d = 20;
        int y = c ^ d;
        //00001010
        //00010100
        System.out.println(y);

    }
}

package Itheima03;

public class test {
    public static void main(String[] args) {

//        int i = 1, j = ++i + ++i + i--;
//        System.out.println(i);
//        System.out.println(j);

        int c = 0;
        for (int i = 10; i <= 50; i++) {
            if (i%2==0&&i%3!=0){

                c++;
                System.out.println(i);

            }
        }
        System.out.println(c);



    }
}

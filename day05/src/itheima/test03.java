package itheima;

public class test03 {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 100; i <= 999; i++) {

            int g = i % 10;
            int b = i / 10 % 10;
            int s = i / 100 % 10;


            if (i == g * g * g + b * b * b + s * s * s) {



                System.out.print(i+"\t");
                count++;


                if (count % 2 == 0) {

                    System.out.println(i);

                }


            }



        }

    }
}

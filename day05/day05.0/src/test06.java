public class test06 {

//    输出三位数的水仙花数 ,每2个数字换行

    public static void main(String[] args) {

        int c = 0;

        for (int i = 100; i < 1000; i++) {

            int g = i % 10;
            int s = i / 10 % 10;
            int b = i / 100 % 10;

            if (i == g * g * g + s * s * s + b * b * b) {

                System.out.print(i + " ");

                c++;

                if (c % 2 == 0) {

                    System.out.println();
                }


            }

        }
    }
}

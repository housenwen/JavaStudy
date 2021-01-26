package homework;

public class test {

        public static void main(String[] args) {
            String dir = "E:\\Workspace\\201909\\day14\\RegexTest.java";
            String[] arr = dir.split("\\\\");
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
        }
    }


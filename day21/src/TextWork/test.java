package TextWork;

public class test {
    public static void main(String[] args) {
        try{
            String s = "hello";
            String sub = s.substring(0);
            System.out.println(sub);
            s = null;
            System.out.println(s);
        }catch(NullPointerException e){
            System.out.println("空指针异常");
        }
    }
}

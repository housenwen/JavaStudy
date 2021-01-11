package ClassWork01;

public class testStudent {
    public static void main(String[] args) {

        Student s = new Student();

        try {
            s.setSocer(-29) ;
        } catch (ScoreException e) {
            System.out.println(e);
        }

       try {
           s.setSocer(89999);
       }catch (ScoreException e){
           System.out.println(e);
       }

        s.setName("张三");

        System.out.println(s);

    }
}

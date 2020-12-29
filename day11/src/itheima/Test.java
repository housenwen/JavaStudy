package itheima;

public class Test {
    static int i;
    public int aMethod( ){
        i++;
        return i;
    }
    public static void main(String [] args){
        Test test = new Test( );
        test.aMethod( );
        System.out.println(test.aMethod( ));
    }
}


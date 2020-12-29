package itheima01;

public class Test{
    int count = 9;
    public void count1(){
        int count =10;
        System.out.println("count1="+count);
    }
    public void count2(){
        System.out.println("count2="+count);
    }
    public static void main(String args[]){
        Test t=new Test();
        t.count1();
        t.count2();
    }
}

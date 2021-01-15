package JUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

//    @Test
//    public void add() {
//        Calculator cal = new Calculator();
//        int num = cal.add(10,20);
//        System.out.println("add="+num);
//    }
//    @Test
//    public void test1(){
//        System.out.println("test1~~~~~~~~~~~~");
//    }

    @Before
    public void setUp() throws Exception {
        System.out.println("before方法执行");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after方法执行");
    }

    @Test
    public void testAdd() {
        // System.out.println("Hello Test~~");
        Calculator c = new Calculator();
        int add = c.add(10, 10);
        System.out.println("add = " + add);
    }
    @Test
    public void testSub() {
         Calculator c = new Calculator();
        System.out.println(c.add(10, 10));
    }
}
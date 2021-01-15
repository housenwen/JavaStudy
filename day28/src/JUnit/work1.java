package JUnit;

import org.junit.Assert;
import org.junit.Test;

public class work1 {
    @Test
    public void testMultiply() {
        Calculator cal = new Calculator();

        int multiply = cal.multiply(2, 2);
        System.out.println("multiply = " + multiply);
        //断言
        Assert.assertEquals(4,multiply);

        int multiply1 = cal.multiply(2, 3);
        System.out.println("multiply1 = " + multiply1);
        //断言
        Assert.assertEquals(6,multiply1);

    }
}

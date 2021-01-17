package JUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalcuateTest {
    Calcuate calcuate ;
    @Before
    public void setUp() throws Exception {
        if (calcuate == null) {
            calcuate = new Calcuate();
        }
    }

    @After
    public void tearDown() throws Exception {
        calcuate = null;
    }

    @Test
    public void add() {
        int result = calcuate.add(2, 3);
        Assert.assertEquals("加法有问题", 5, result);
    }

    @Test
    public void subtract() {
        int result = calcuate.subtract(12, 2);
        // 故意设置减法期望值为10000
        Assert.assertEquals("减法有问题", 100, result);
    }

    @Test
    public void multiply() {
        int result = calcuate.multiply(2, 3);
        Assert.assertEquals("乘法有问题", 6, result);
    }

    @Test
    public void divide() {
        int result = calcuate.divide(6, 3);
        Assert.assertEquals("除法有问题", 2, result);
    }

}
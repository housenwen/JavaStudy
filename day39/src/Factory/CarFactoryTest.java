package Factory;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarFactoryTest {

    @Test
    public void createCar() {
        CarFactory cf = new CarFactory();
        Car bmw = cf.createCar("BMW-2030");
        Car benz = cf.createCar("Benz-2345");
        bmw.run();
        benz.run();

    }
}
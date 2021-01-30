package JDK8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CarTest {
    final Car car = Car.create( Car::new );
    final List< Car > cars = Arrays.asList( car );

    @Test
    public void create() {
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );
    }

    @Test
    public void collide() {
        cars.forEach( Car::collide );
    }

    @Test
    public void follow() {
        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );
    }

    @Test
    public void repair() {
        cars.forEach( Car::repair );
    }
    @Test
    public void run(){
        List names = new ArrayList();

        names.add("大明");
        names.add("二明");
        names.add("小明");

        names.forEach(System.out::println);

    }
}
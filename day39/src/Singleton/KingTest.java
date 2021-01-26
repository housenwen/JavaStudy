package Singleton;

import org.junit.Test;

import static org.junit.Assert.*;

public class KingTest {

    @Test
    public void getInstance() {
        System.out.println(King.getInstance());
    }
}
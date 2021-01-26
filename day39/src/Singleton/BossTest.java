package Singleton;

import org.junit.Test;

import static org.junit.Assert.*;

public class BossTest {

    @Test
    public void getInstance() {
        System.out.println(Boss.getInstance());
    }
}

package Junit1;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void show() {
        new Student().show();
    }
}
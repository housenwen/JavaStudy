package Annotation;

import Interface.Retention;
import Interface.Target;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface MyTest {

}

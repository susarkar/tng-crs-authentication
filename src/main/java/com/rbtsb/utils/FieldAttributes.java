package com.rbtsb.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by kent on 29/11/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldAttributes {

    boolean nullable() default true;

    int min() default Integer.MIN_VALUE;

    int max() default Integer.MAX_VALUE;

    String[] dateFormat() default {};

    int size() default 0;

    String[] definedValues() default {};

    String[] notNullIf() default {};

}

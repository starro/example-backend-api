package com.starro.bodoc.common.engine.annotation.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @since       2019.07.02
 * @author      starro
 * @description description
 **********************************************************************************************************************/
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

	String value() default "";
}
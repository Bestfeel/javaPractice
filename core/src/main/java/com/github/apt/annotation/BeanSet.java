/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more.
 */

package com.github.apt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by feel on 16/9/1.
 */
@Retention(value = RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface BeanSet {
    String value() default "value";
}
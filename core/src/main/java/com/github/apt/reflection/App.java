/*
 * Copyright (c) 2017.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more.
 */

package com.github.apt.reflection;


import com.github.apt.annotation.BeanSet;
import com.github.apt.service.IHandle;
import com.github.apt.service.PayHandle;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        Reflections reflections = new Reflections("com.gizwits");

        //  扫描注解类
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(BeanSet.class);

        for (Class<?> aClass : typesAnnotatedWith) {

            System.out.println(aClass);
        }

        System.out.println("..........");


        // 扫描接口子类的实现
        Set<Class<? extends IHandle>> subTypesOf = reflections.getSubTypesOf(IHandle.class);

        for (Class<? extends IHandle> nulls : subTypesOf) {
            System.out.println(nulls);
        }

        Set<Method> allMethods = ReflectionUtils.getAllMethods(PayHandle.class, null);

        for (Method allMethod : allMethods) {

//            try {
//                allMethod.invoke(new PayHandle(), null);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }

            System.out.println(allMethod);
        }


    }
}

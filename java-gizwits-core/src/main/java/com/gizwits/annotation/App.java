/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */
package com.gizwits.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by feel on 16/9/1.
 */
public class App {

    /**
     * 检查一个类是否包含一个特定的注解类型
     *
     * @param annotationType
     * @param clazz
     * @return
     */
    public static boolean isAnnotationDeclaredLocally(Class annotationType, Class clazz) {
        boolean declaredLocally = false;
        Iterator i$ = Arrays.asList(clazz.getDeclaredAnnotations()).iterator();
        do {
            if (!i$.hasNext())
                break;
            Annotation annotation = (Annotation) i$.next();
            if (!annotation.annotationType().equals(annotationType))
                continue;
            declaredLocally = true;
            break;
        } while (true);
        return declaredLocally;
    }

    public static Method methodOf(Class clazz, Class cls) {
        for (Method method : clazz.getMethods()) {


            Annotation annotation = method.getAnnotation(cls);

            if (annotation != null) {
                return method;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

//通过反射方法获取并且注入


        Class cls = Class.forName("com.gizwits.annotation.Process");


        boolean a = isAnnotationDeclaredLocally(Color.class, cls);

        System.out.println(a);


        Bean process = (Bean) cls.newInstance();

        //  Field[] fields = cls.getDeclaredFields();

//        for (Field field : fields) {
//
//            Annotation[] annotations = field.getDeclaredAnnotations();
//
//            System.out.println(Arrays.toString(annotations));
//
//            Color color = field.getAnnotation(Color.class);
//            System.out.println(color.value());
//            // 设置私有字段访问
//            field.setAccessible(true);
//
//
//        }


        Method method = methodOf(cls, Color.class);

        // System.out.println(method);


        // method.invoke(cls.newInstance(), "");


        Parameter[] parameters = method.getParameters();

        for (Parameter p : parameters) {


            if (p.getType().equals(String.class)) {
                System.out.println(p.getName() + "-----" + p.getType());

            }
        }

        // System.out.println(Arrays.toString(parameters));

        // end  main
    }
}

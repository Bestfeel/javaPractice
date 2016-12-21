/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.gizwits.proxy;

import com.gizwits.bean.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by feel on 16/4/3.
 */
public class PersonInvocationHandler implements InvocationHandler {

    private Person person;

    public PersonInvocationHandler(Person person) {
        this.person = person;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("人吃饭之前要洗手.....");
        Object invoke = method.invoke(person, args);

        System.out.println("人吃饭之后要洗碗...");
        //   这里可以没有返回值,具体看方法是否需要返回值.
        return invoke;
    }
}

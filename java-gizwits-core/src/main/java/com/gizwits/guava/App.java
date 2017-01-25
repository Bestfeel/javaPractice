/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */
package com.gizwits.guava;

import com.google.common.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

interface IFace {

    String show();
}

class FaceImpl implements IFace {

    @Override
    public String show() {

        return "show";
    }
}

public class App {

    public static void main(String[] args) {

        FaceImpl face = new FaceImpl();

        IFace iFace = Reflection.newProxy(IFace.class, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println(method);

                System.out.println(Arrays.toString(args));

                Object invoke = method.invoke(face, args);

                return "干啥 >>" + invoke + "..干你咋了";
            }
        });


        String show = iFace.show();

        System.out.println(show);


        // end  main
    }

}

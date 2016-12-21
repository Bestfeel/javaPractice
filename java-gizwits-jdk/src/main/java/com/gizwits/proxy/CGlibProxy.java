/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.gizwits.proxy;

import com.gizwits.bean.User;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by feel on 16/4/3.
 */
public class CGlibProxy {

    public static void main(String[] args) {



        //cglib 中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();

        //设置要创建动态代理的类
        enhancer.setSuperclass(User.class);
        // 设置回调(方法拦截器)，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
        enhancer.setCallback(new CGlibInterceptor("hello"));

        User u = (User) enhancer.create();
        String hello = u.hello();
        System.out.println(hello);
        String eat = u.eat();

        System.out.println(eat);


        //end  main
    }
}

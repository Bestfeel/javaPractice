package com.github;

import com.github.service.Foo;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AppTest {

    @Test
    public void test1() throws Exception {
        Enhancer enhancer = new Enhancer();

//        enhancer.setInterfaces(new Class[]{Foo.class});


        enhancer.setCallback((MethodInterceptor) (obj, method, objects, methodProxy) -> {
            return methodProxy.invokeSuper(obj, objects);
        });
        Foo foo = (Foo) enhancer.create();
        System.out.println(foo);
        System.out.println(foo.hi());
    }
}

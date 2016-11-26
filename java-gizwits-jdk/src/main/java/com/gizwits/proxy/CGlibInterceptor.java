package com.gizwits.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by feel on 16/4/3.
 */
public class CGlibInterceptor implements MethodInterceptor {

    CGlibInterceptor(String name) {


        System.out.println("CGlibInterceptor...." + name);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        //System.out.println("人吃饭之前要洗手.....");

        System.out.println(Arrays.toString(args));

        String invoke = (String) proxy.invokeSuper(obj, args);


        //System.out.println("人吃饭之后要洗碗...");
        //   这里可以没有返回值,具体看方法是否需要返回值.
        return "--吃饭前----" + invoke + "----吃饭后------";

    }
}

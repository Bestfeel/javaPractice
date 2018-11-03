package com.github.proxy;

/**
 * <p>
 * 使用jdk 原生的方式实现动态代理
 */
public class JDKPorxy {

    public static void main(String[] args) {

        // Person p = new Person();

        //   这里创建一个动态代理类.人要吃饭之前要 洗手, 吃饭之后还有洗碗.
        // Object o = Proxy.newProxyInstance(p.getClass().getClassLoader(), p.getClass().getInterfaces(), new PersonInvocationHandler(p));

        // 必须使用接口
        //   IPerson person = (IPerson) o;

        //  person.eat();


        //end  main

    }
}

package com.github.main;

import com.github.bean.User;
import com.github.service.Foo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.management.ManagementFactory;

public class TestApp {
    public static void main(String[] args) {

        // 当构造方案已经初始化了包扫描，不能再次refresh
        //  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.github");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 手动添加扫描包,需要refresh
        context.scan("com.github");
        context.refresh();


        Foo bean = context.getBean(Foo.class);

        System.out.println(bean.hi());

//        System.out.println(context.getBean(User.class));
        System.out.println(context.getBean("user1"));
        System.out.println(context.getBean("user2"));
        System.out.println(context.getBean("user3"));
        System.out.println(context.getBean("user4"));


        //第一种方法:获取启动类
        System.out.println(getMainClassName());
        // 第二种方法：获取启动类
        System.out.println(ManagementFactory.getRuntimeMXBean().getSystemProperties().get("sun.java.command"));
//        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
//
//
//        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext();
//
//
//        xmlApplicationContext.start();


    }

    public static String getMainClassName() {
        StackTraceElement[] stackTraceElements = new RuntimeException().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            if ("main".equals(stackTraceElement.getMethodName())) {
                return stackTraceElement.getClassName();
            }
        }
        return "";
    }
}

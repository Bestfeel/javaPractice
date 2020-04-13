package com.github.main;

import com.github.bean.BeanConfig;
import com.github.bean.User;
import com.github.service.FooService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathXmlApplicationContextTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        User user = context.getBean(User.class);
        System.out.println(user);

        FooService service = context.getBean(FooService.class);

        service.test();
    }
}

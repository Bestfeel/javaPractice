package com.github.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Feel
 * @date 2018/11/4
 * @since 1.0
 * http://fangjian0423.github.io/2017/05/10/springboot-context-refresh/
 */
@SpringBootApplication(scanBasePackages = {"com.github"})
public class App {

    public static void main(String[] args) {
        // Class类型，使用AnnotatedBeanDefinitionReader加载
        // 加载完毕之后使用ConfigurationClassPostProcessor进行后续的处理
        SpringApplication.run(App.class, args);
    }
}

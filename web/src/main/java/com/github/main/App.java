package com.github.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Feel
 * @date 2018/11/4
 * @since 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.github"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

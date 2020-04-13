package com.github.service;

import org.springframework.stereotype.Component;

@Component
public class FooImpl implements Foo {
    @Override
    public String hi() {
        return "hello  world";
    }
}

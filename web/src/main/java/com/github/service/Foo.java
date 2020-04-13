package com.github.service;

public interface Foo {

    default String hi() {
        return "hello world";
    }
}

/*
 * Copyright (c) 2017.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more.
 */

package com.gizwits.main;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

public class JavaPoetTest {

    public static void main(String[] args) throws IOException {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet,best feel!")
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
                .build();

        javaFile.writeTo(System.out);


        String path = JavaPoetTest.class.getClassLoader().getResource(".").getPath();


        javaFile.writeTo(new File(new File(path).getParentFile().getParentFile().getPath() + "/src/main/java"));


    }
}

/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.github.apt.process;


import com.github.apt.annotation.BeanSet;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by feel on 2016/12/16.
 * //@SupportedAnnotationTypes(value = {"com.gizwits.annotation.BeanSet"})
 */
@AutoService(Processor.class)
public class ProcessProcessor extends AbstractProcessor {


    private Messager messager;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        //错误处理,获取消息处理
        this.messager = processingEnv.getMessager();
        //Filer是个接口，支持通过注解处理器创建新文件
        this.filer = processingEnv.getFiler();

        System.out.println("....init....");

    }


    /**
     * 通过重写该方法，告知Processor哪些注解需要处理。
     * 集合内容为自定义注解的包名+类名。
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>();
        annotataions.add(BeanSet.class.getCanonicalName());
        return annotataions;
    }

    /**
     * 用来指定你使用的Java版本
     * 通常这里返回SourceVersion.latestSupported()
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {

            // 遍历所有被注解了 的元素
            for (TypeElement te : annotations) {


                for (Element e : roundEnv.getElementsAnnotatedWith(te)) {
                    System.out.println(e.asType());
                    //  Diagnostic.Kind 是一个枚举类
                    messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.toString());
                }


            }
        } catch (Exception e1) {
            //输出日志
            messager.printMessage(Diagnostic.Kind.ERROR, "Couldn't generate class");

            e1.printStackTrace();
        }
        return true;
    }
}

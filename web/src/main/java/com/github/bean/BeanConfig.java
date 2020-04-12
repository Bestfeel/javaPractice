package com.github.bean;

import com.github.annotion.MyAutowired;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.Set;

@Configuration
public class BeanConfig {

//    /**
//     * 方法一
//     * @return
//     */
//    @Bean(name = AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = Sets.newHashSet(Autowired.class, Value.class, Inject.class, MyAutowired.class);
//        processor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return processor;
//    }


    /**
     * 方法二：兼容原来的注解
     *
     * @return
     */
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setAutowiredAnnotationType(MyAutowired.class);
        return processor;
    }

}

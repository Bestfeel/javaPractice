package com.github.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 方法一：
        BeanDefinitionReaderUtils.registerBeanDefinition(new BeanDefinitionHolder(new AnnotatedGenericBeanDefinition(User.class), "user1"), registry);

        // 方法二：
        registry.registerBeanDefinition("user2", new AnnotatedGenericBeanDefinition(User.class));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        // 方法三
        beanFactory.registerSingleton("user3", new User());

    }
}

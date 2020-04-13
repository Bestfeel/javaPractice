package com.github.controller;

import com.alibaba.fastjson.JSON;
import com.github.service.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import javax.annotation.Resource;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * @author Feel
 * @date 2018/11/4
 * @since 1.0
 */
@RestController
@RequestMapping("/")
@Slf4j
public class IndexController {

    /**
     * @see BeanFactory
     * @see DefaultListableBeanFactory
     */
    @Autowired
    private DefaultListableBeanFactory beanFactory;

    @Resource
    private ConfigurableWebApplicationContext context;

    @GetMapping("index")
    public String index() throws Exception {

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

        System.out.println(JSON.toJSONString(runtimeMXBean));
        System.out.println(beanFactory);
        Foo bean = beanFactory.getBean(Foo.class);
        System.out.println(bean.hi());
        return "hello wolrd";
    }

    @GetMapping("refresh")
    public String refresh() throws Exception {
        // 这个刷新会导致服务停止
        context.refresh();
        return "hello wolrd";
    }

}

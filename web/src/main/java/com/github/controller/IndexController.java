package com.github.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Feel
 * @date 2018/11/4
 * @since 1.0
 */
@RestController
@RequestMapping("/")
@Slf4j
public class IndexController {

    @GetMapping("index")
    public String index() throws Exception {

        log.error("IndexController index error");
        log.debug("IndexController index  debug");
        log.info("IndexController index info");
        log.warn("IndexController index warn");
        return "hello wolrd";
    }
}

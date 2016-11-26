package com.gizwits.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by feel on 16/9/8.
 */
@Data
@Slf4j
@AllArgsConstructor
public class Person {
    private int age;
    private String name;

}

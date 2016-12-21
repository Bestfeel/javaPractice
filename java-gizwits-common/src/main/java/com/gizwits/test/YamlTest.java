/*
 * Copyright (c) 2016.  feel ,www.feel88.cn
 * This program is my java practice.you  will be learn more .
 */

package com.gizwits.test;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by feel on 16/6/10.
 */
public class YamlTest {

    public static void main(String[] args) throws Exception {


        String path = YamlTest.class.getClassLoader().getResource("config.yml").getPath();


        System.out.println(path);
        InputStream input = new FileInputStream(path);
        Yaml yaml = new Yaml();


        List<Map<String, Object>> object = (List<Map<String, Object>>) yaml.load(input);

        Map<String, Object> map = object.get(0);

        for (String key : map.keySet()) {

            System.out.println(key + "--->" + map.get(key));
        }


    }
}

package com.github.service;

import com.github.annotion.MyAutowired;
import com.github.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooService {

    @Autowired
    private User user;

    @MyAutowired
    private User myUser;

    public void test() {
        System.out.println("----user-" + user);
        System.out.println("----myUser-" + myUser);
    }
}

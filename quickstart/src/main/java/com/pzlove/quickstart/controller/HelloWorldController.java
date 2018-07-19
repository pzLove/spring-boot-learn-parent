package com.pzlove.quickstart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pzlove
 * @date 2018-07-10 下午5:44
 **/
@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello,World!";
    }
}

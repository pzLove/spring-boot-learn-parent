package com.pzlove.jpa.controller;

import com.pzlove.jpa.domain.User;
import com.pzlove.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }
    @PostMapping("/query")
    public void queryUser(@RequestBody User user){
        userService.findUser(user.getId());
    }

}
package com.pzlove.jpa.service;

import com.pzlove.jpa.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService service;

    @Test
    public void saveUser() {
        //insert
        //User user=new User(null,"pzl",27,"信阳市");
        //update
        User user=new User(2L,"pzl",24,"信阳市","13774469589");
        service.saveUser(user);
    }

    @Test
    public void findUser(){
        User user=service.findUser(1L);
        System.out.println(user);
    }
}
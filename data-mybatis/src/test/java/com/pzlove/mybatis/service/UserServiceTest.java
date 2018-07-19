package com.pzlove.mybatis.service;

import com.pzlove.mybatis.domain.City;
import com.pzlove.mybatis.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findUserById() {
        City city=cityService.findCityById(1L);
        User user=userService.findUserById(1L);
        user.setCity(city.getCityName());
        userService.updateUser(user);
        User u=userService.findUserById(1L);
        System.out.println(u);

    }
}
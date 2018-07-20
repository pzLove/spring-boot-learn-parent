package com.pzlove.web.service;

import com.alibaba.fastjson.JSONObject;
import com.pzlove.web.WebLearnApplication;
import com.pzlove.web.pojo.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebLearnApplication.class)
public class CityServiceTest {

    @Autowired
    private CityService cityService;
    private City city;

    @Before
    public void setUp() throws Exception {
        System.out.println("test start.........");
        city=new City(23,2,"信阳","我的家");
    }

    @Test
    public void findAllCity() {
        List<City> cities=cityService.findAllCity();
        System.out.println(JSONObject.toJSONString(cities));
    }

    @Test
    public void saveCity() {
        cityService.saveCity(city);
        System.out.println("保存成功");
    }

    @Test
    public void findCity() {
        City c=cityService.findCityById(22L);
        System.out.println(JSONObject.toJSONString(c));
    }
}
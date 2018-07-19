package com.pzlove.quickstart.controller;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class HelloWorldControllerTest {

    @Test
    public void sayHello() {
        System.out.println(new HelloWorldController().sayHello());
        assertEquals("Hello,World!",new HelloWorldController().sayHello());
    }
}
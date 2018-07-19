package com.pzlove.quickstart;

import com.pzlove.quickstart.config.BlogProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuickStartApplicationTests {

	@Autowired
	private BlogProperties properties;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getHello() throws Exception {
		System.out.println(properties.getName()+":::"+properties.getTitle());

		Assert.assertEquals(properties.getName(), "程序猿DD");
		Assert.assertEquals(properties.getTitle(), "Spring Boot教程");
	}
}

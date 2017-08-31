package com.petter.test;

import com.petter.properties.BlogProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author hongxf
 * @since 2017-08-31 9:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
/*
 * @ActiveProfiles，可以指定一个或者多个 profile，
 * 这样我们的测试类就仅仅加载这些名字的 profile 中定义的 bean 实例。
 * 这里激活application-prod.properties配置文件.
 */
@ActiveProfiles("prod")
public class AppTest {

    @Resource
    private BlogProperties blogProperties;

    @Test
    public void testBlog() throws Exception {
        System.out.println("AppTest.testBlog()=" + blogProperties);
        Assert.assertEquals("hongxf", blogProperties.getName());
        Assert.assertEquals("Spring Boot教程", blogProperties.getTitle());
    }
}

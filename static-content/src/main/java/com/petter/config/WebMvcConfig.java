package com.petter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义静态资源目录
 * @author Administrator
 * @since 2017-02-12 0:10
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //可以多次使用 addResourceLocations 添加目录，优先级先添加的高于后添加的。
        //这里使用了硬盘资源，可以正常访问
        registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/", "file:E:/1.jpg");
        super.addResourceHandlers(registry);
    }

}

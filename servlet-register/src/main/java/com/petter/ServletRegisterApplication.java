package com.petter;

import com.petter.servlet.DemoFilter;
import com.petter.servlet.DemoServlet;
import com.petter.servlet.DemoServletContextListener;
import com.petter.servlet.DemoSessionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 两种注册Servlet，Filter和Listener的方式
 * 1. 在各自的类上使用@WebServlet，@WebFilter，@WebListener注解，然后使用@ServletComponentScan注解进行扫描
 * 2. 使用ServletRegistrationBean，FilterRegistrationBean，ServletListenerRegistrationBean进行手动注册
 *
 * 在独立的Tomcat容器中，只能使用第二种方式
 */
@SpringBootApplication
//@ServletComponentScan
public class ServletRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletRegisterApplication.class, args);
	}

    @Bean
    public ServletRegistrationBean demoServlet() {
        return new ServletRegistrationBean(new DemoServlet(), "/my-servlet");
    }

    @Bean
    public FilterRegistrationBean demoFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("my-filter");
        filterRegistrationBean.setFilter(new DemoFilter());
        List<String> urlList = new ArrayList<>();
        urlList.add("/*");
        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean demoServletContextListener() {
        return new ServletListenerRegistrationBean<>(new DemoServletContextListener());
    }

    @Bean
    public ServletListenerRegistrationBean demoSessionListener() {
        return new ServletListenerRegistrationBean<>(new DemoSessionListener());
    }
}

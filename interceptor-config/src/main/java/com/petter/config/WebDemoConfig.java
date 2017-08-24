package com.petter.config;

import com.petter.interceptor.MyInterceptor1;
import com.petter.interceptor.MyInterceptor2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Administrator
 * @since 2017-02-12 0:10
 */
@Configuration
public class WebDemoConfig extends WebMvcConfigurerAdapter {

    /**
     *  只有经过DispatcherServlet 的请求，才会走拦截器链
     *  自定义的Servlet请求是不会被拦截的，比如自定义的Servlet地址http://localhost:8080/my-servlet是不会被拦截器拦截的。
     *  并且不管是属于哪个Servlet只要符合过滤器的过滤规则，过滤器都会拦截。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //多个拦截器组成一个拦截器链 addPathPatterns 用于添加拦截规则 excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}

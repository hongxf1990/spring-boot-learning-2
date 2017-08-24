package com.petter.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author hongxf
 * @since 2017-08-24 14:14
 */
//@WebFilter(filterName="my-filter",urlPatterns="/*")
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行过滤操作");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}

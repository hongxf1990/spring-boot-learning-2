package com.petter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring Boot默认提供了一个/error路径处理所有错误，并且有一个默认的错误页面
 * 1. 可以在public中建立error文件夹，建立错误码命名的页面名称（可以是html，ftl等动态页面），Spring Boot会自动找到该页面
 * 2. 使用这个类处理系统抛出的所有异常，有以下两种方式
 *
 * @author Administrator
 * @since 2017-02-10 20:46
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    //第一种方式直接返回一个Json
    //@ExceptionHandler
    //@ResponseBody
    public String defaultErrorHandler(
            HttpServletRequest req, Exception e) {
        System.out.println(e.getMessage());
        System.out.println("这里进行了异常处理");
        return "aa";
    }

    //第二种方式直接返回一个页面
    @ExceptionHandler
    public ModelAndView defaultErrorHandler2(
            HttpServletRequest request, Exception e) {
        HttpStatus status = getStatus(request);
        ModelAndView mav = new ModelAndView();
        mav.addObject("status", status.value());
        mav.addObject("content", "我们处理了异常啦");
        mav.setViewName("error/500"); //需要在templates中建立渲染页面
        return mav;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}

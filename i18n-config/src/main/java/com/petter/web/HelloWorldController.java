package com.petter.web;

import com.petter.entity.Demo;
import com.petter.i18n.LocaleMessageSourceUtil;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author hongxf
 * @since 2017-08-23 09:35
 */
@Controller
public class HelloWorldController {

    @Resource
    private MessageSource messageSource;
    @Resource
    private LocaleMessageSourceUtil localeMessageSourceUtil;

    @RequestMapping("/hello")
    public String hello(){

        return  "/hello";
    }

    @RequestMapping("/hello2")
    public String helloHtml(
            @ModelAttribute(name = "demo1") Demo demo
    ) {
        //代码中使用i18n
        /*Locale locale = LocaleContextHolder.getLocale(); //常用
        String welcome = messageSource.getMessage("welcome", null, locale);

        System.out.println(welcome);*/

        //直接使用封装好的
        String welcome1 = localeMessageSourceUtil.getMessage("welcome");

        System.out.println(welcome1);
        demo.setName(welcome1);
        return "hello2";
    }

    @RequestMapping(value = "/changeSessionLanguage", method = RequestMethod.GET)
    public String changeSessionLanguage(HttpServletRequest request,
                                        HttpServletResponse response, String lang){
        /*if("zh".equals(lang)){
            //设置会话的区域
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
                    new Locale("zh", "CN"));
        } else if("en".equals(lang)){
            request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
                    new Locale("en", "US"));
        }
        return "redirect:/hello";*/

        //不管是SessionLocaleResolver 还是 CookieLocaleResolver 都可以，推荐使用
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if("zh".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        } else if("en".equals(lang)) {
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }
        return "redirect:/hello";
    }
}

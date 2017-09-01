package com.petter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author hongxf
 * @since 2017-08-31 11:34
 */
@Configuration
public class i18nConfig {

    /**
     * 会话区域解析器也就是说，只针对当前的会话有效，session失效，还原为默认状态
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
       SessionLocaleResolver slr = new SessionLocaleResolver();
       //设置默认区域
       slr.setDefaultLocale(Locale.CHINA);
       return slr;
    }

    /**
     * Cookie区域解析器，使用Cookie设置区域解析
     * @return
     */
    /*@Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
        //设置默认区域,
        slr.setDefaultLocale(Locale.CHINA);
        slr.setCookieMaxAge(3600);//设置cookie有效期.
        return slr;
    }*/
}

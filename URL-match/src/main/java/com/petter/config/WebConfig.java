package com.petter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author hongxf
 * @since 2017-09-06 9:34
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * setUseSuffixPatternMatch(boolean useSuffixPatternMatch)：
     * 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认true 即匹配；
     * 当此参数设置为true的时候，那么/user.html，/user.aa，/user.*都能是正常访问的。
     * 当此参数设置为false的时候，那么只能访问/user
     *
     * setUseTrailingSlashMatch(boolean useSuffixPatternMatch)：
     * 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
     * 当此参数设置为true的后，那么地址/user，/user/都能正常访问。
     * 当此参数设置为false的时候，那么就只能访问/user了。
     *
     * 当以上两个参数都设置为true的时候，那么路径/user或者/user.aa，/user.*，/user/都是能正常访问的，但是类似/user.html/ 是无法访问的。
     * 当都设置为false的时候，那么就只能访问/user路径了。
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
    }
}

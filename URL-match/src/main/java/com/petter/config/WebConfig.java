package com.petter.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

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

    /**
     * (406)Could not find acceptable representation原因及解决方法
     * 比如通过/login.html请求/login本应该返回json数据，系统会根据html去找html，类型不匹配报错
     * favorPathExtension表示支持后缀匹配，
     * 属性ignoreAcceptHeader默认为false，表示accept-header匹配
     * defaultContentType开启默认匹配。
     * 例如：请求aaa.xx，若设置<entry key="xx" value="application/xml"/> 也能匹配以xml返回。
     * 根据以上条件进行一一匹配最终，得到相关并符合的策略初始化ContentNegotiationManager
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    /**
     * 配置使用fastjson取代jackson，看自己的使用习惯了
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //FastJsonHttpMessageConverter 用于Spring MVC4.2以下，FastJsonHttpMessageConverter4用于4.2以上
        FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fastConverter);
    }
}

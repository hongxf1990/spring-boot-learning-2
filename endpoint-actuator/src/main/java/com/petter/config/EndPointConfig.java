package com.petter.config;

import com.petter.endpoint.MyEndPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hongxf
 * @since 2017-09-14 9:54
 */
@Configuration
public class EndPointConfig {

    @Bean
    public MyEndPoint myEndPoint() {
        return new MyEndPoint();
    }
}

package com.petter.web;

import com.petter.properties.AbcProperties;
import com.petter.properties.PetterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hongxf
 * @since 2017-08-23 09:35
 */
@RestController
@EnableConfigurationProperties({PetterProperties.class})
public class HelloWorldController {

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Resource
    private PetterProperties petterProperties;
    @Resource
    private AbcProperties abcProperties;

    @RequestMapping("/hello-world")
    public PetterProperties hello(){
        return petterProperties;
    }

    @RequestMapping("/abc")
    public AbcProperties hello2() {
        return abcProperties;
    }

}

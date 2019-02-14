package com.abc.petter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongxf
 * @since 2019-02-13 16:57
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        Girl girl = new Girl();
        girl.setId(1);
        girl.setName("lele");
        girl.setAge(20);
        girl.setWeight(100.0);
        return girl.toString();
    }
}

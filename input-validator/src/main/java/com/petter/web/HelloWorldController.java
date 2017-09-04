package com.petter.web;

import com.petter.entity.Demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author hongxf
 * @since 2017-08-23 09:35
 */
@Controller
public class HelloWorldController {

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/hello-world")
    public String demo(Model model){
        model.addAttribute("demo", new Demo());
        return "demo";
    }


    @RequestMapping("/demo-add")
    public String demoAdd(
            @Valid Demo demo, //@Valid指定要校验的实体类
            BindingResult result, // 所有的错误信息都会保存在这个类中，可以使用result.hasErrors()  判断是否有错误信息
            Model model
    ){
        //有错误信息.
        model.addAttribute("demo",demo);

        //添加自定义的错误信息，用于注解无法表示的时候
        result.rejectValue("name", "misFormat", "这个name已经注册过了！");

        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError  error:list){
                System.out.println(error.getCode() + "---" + Arrays.toString(error.getArguments()) + "---"+error.getDefaultMessage());
            }
        }
        return "demo";
    }

}

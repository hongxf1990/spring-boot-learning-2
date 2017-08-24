package com.petter.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 服务启动完成前执行
 * order数字越小越先执行
 * @author Administrator
 * @since 2017-02-12 20:35
 */
@Component
@Order(2)
public class StartupRunner2 implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行22222，执行加载数据等操作<<<<<<<<<<<<<");
    }
}

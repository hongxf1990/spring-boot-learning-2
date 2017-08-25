package com.petter.service;

import com.petter.entity.Demo;
import com.petter.repository.DemoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hongxf
 * @since 2017-08-24 17:08
 */
@Service
public class DemoService {

    @Resource
    private DemoRepository demoRepository;

    //这里的单引号不能少，否则会报错，被识别是一个对象;
    private static final String CACHE_KEY = "'demo'";

    /**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
     */
    private static final String DEMO_CACHE_NAME = "demo";

    /**
     * 保存数据.
     * @param demo
     */
    @CacheEvict(value=DEMO_CACHE_NAME, key=CACHE_KEY)
    public Demo save(Demo demo){
        return demoRepository.save(demo);
    }

    /**
     * 查询数据
     * 这里使用@Cacheable必须指定value值，即缓存的名称，否则报错
     * 在这个缓存名称下面包含多个查询缓存
     * @param id
     * @return
     */
    @Cacheable(value=DEMO_CACHE_NAME, key="'demo_' + #id")
    public Demo findById(Long id) {
        System.out.println("没有走缓存！" + id);
        return demoRepository.findOne(id);
    }

    /**
     *
     * 修改数据.
     *
     * 在支持Spring Cache的环境下，对于使用@Cacheable标注的方法，Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，
     * 而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。@CachePut也可以声明一个方法支持缓存功能。
     * 与@Cacheable不同的是使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，
     * 而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
     *
     * @param updated
     */
    @CachePut(value = DEMO_CACHE_NAME, key = "'demo_' + #updated.getId()")
    public Demo update(Demo updated) {
        Demo demoInfo = demoRepository.findOne(updated.getId());

        demoInfo.setName(updated.getName());
        demoInfo.setPwd(updated.getPwd());
        return demoInfo;
    }

    /**
     * 删除缓存时候需要指定key的值
     * 现在使用的KeyGenerator规则不好，无法根据参数来得到key，需要改进
     * @param id
     */
    @CacheEvict(value=DEMO_CACHE_NAME, key =  "'demo_' + #id")
    public void delete(Long id) {
        demoRepository.delete(id);
    }
}

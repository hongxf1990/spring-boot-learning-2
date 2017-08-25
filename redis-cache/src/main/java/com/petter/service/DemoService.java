package com.petter.service;

import com.petter.entity.Demo;
import com.petter.repository.DemoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void test() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("redisCache", "random1=" + Math.random());
        System.out.println(valueOperations.get("redisCache"));
    }

    /**
     * 这里使用@Cacheable必须指定value值，即缓存的名称，否则报错
     * 在这个缓存名称下面包含多个查询缓存
     * @param id
     * @return
     */
    @Cacheable(value = "demo")
    public Demo findById(Long id) {
        System.out.println("findById() ==== 从数据库中进行获取的。。。id= " + id);
        return demoRepository.findOne(id);
    }

    /**
     * 删除缓存时候需要指定key的值
     * 现在使用的KeyGenerator规则不好，无法根据参数来得到key，需要改进
     * @param id
     */
    @CacheEvict(value="demo", key = "'com.petter.service.DemoServicefindById' + #id")
    public void deleteFromCache(Long id) {
        System.out.println("deleteFromCache() -- 从缓存中删除.");
    }
}

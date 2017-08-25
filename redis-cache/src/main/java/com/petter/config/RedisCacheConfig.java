package com.petter.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * redis 缓存配置;
 *
 * 注意：RedisCacheConfig这里也可以不用继承CachingConfigurerSupport，也就是直接一个普通的Class就好
 *
 * 这里主要我们之后要重新实现key的生成策略，只要这里修改KeyGenerator，其它位置不用修改就生效了。
 *
 * 使用普通类的方式的话，那么在使用@Cacheable的时候还需要指定KeyGenerator的名称，这样编码的时候比较麻烦。
 * @author hongxf
 * @since 2017-02-14 9:32
 */
@Configuration
@EnableConfigurationProperties({RedisProperties.class})
@EnableCaching //启用缓存，这个注解很重要；
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Resource
    private RedisProperties redisProperties;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(redisProperties.getHost());
        redisConnectionFactory.setPort(redisProperties.getPort());
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisProperties.getPool().getMaxIdle());
        config.setMinIdle(redisProperties.getPool().getMinIdle());
        config.setMaxWaitMillis(redisProperties.getPool().getMaxWait());
        redisConnectionFactory.setPoolConfig(config);
        return redisConnectionFactory;
    }


    /**
     * 缓存管理器.
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
        //可以对缓存管理器进行一些设置
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        //redisCacheManager.setDefaultExpiration(60);//秒
        return redisCacheManager;
    }

    /**
     * redis模板操作类,类似于jdbcTemplate的一个类;
     * 自定义了key和value的序列化方式
     *
     * @param factory : 通过Spring进行注入，参数在application.properties进行配置；
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        //key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
        //所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
        //或者JdkSerializationRedisSerializer序列化方式;当重写keyGenerator以后可以放开
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        //spring-data-redis默认会对key 和 value 进行序列化，因为redis的client和server进行交互时协议要求的是byte
        //采用spring操作redis是在jedis客户端基础上进行的，而jedis客户端与redis交互的时候协议中定义是用byte类型交互
        //所以为了解决key加入\xac\xed\x00\x05t\x00的问题，加入以下序列化定义


        //定义key的序列化方式
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        //定义value的序列化方式
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 自定义key.
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            // This will generate a unique key of the class name, the method name and all method parameters appended.
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
}

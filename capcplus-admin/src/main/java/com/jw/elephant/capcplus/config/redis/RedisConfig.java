package com.jw.elephant.capcplus.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 需要手动配置redis序列化数据，不然从redis服务段读取得数据回出现乱码字符
 */
@Configuration
public class RedisConfig {
   /* @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String ,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        //Json序列化配置
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //String序列号配置
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //key和hash的key都采用String的序列化配置
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        //value和hash的value采用Json的序列化配置
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }*/
   @Bean
   public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
       RedisTemplate<String, Object> template = new RedisTemplate<>();
       template.setConnectionFactory(factory);
       template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
       //key采用string序列化配置
       template.setKeySerializer(new StringRedisSerializer());
       template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
       //value采用jackson序列化配置new GenericJackson2JsonRedisSerializer()
       template.setValueSerializer(new StringRedisSerializer());
       return template;
   }
}

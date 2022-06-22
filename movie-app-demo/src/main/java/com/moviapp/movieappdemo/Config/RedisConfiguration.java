package com.moviapp.movieappdemo.Config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration {
/*
  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  RedisTemplate redisTemplate() {
    RedisTemplate redisTemplate = new RedisTemplate();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }
*/

}

package com.backend.aggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.aggregator.pubsub.RedisPubSub;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Configuration
@Slf4j
public class RedisConfig {

    @Bean
    public JedisPooled jedisConnection() {
        JedisPooled jedis = new JedisPooled("127.0.0.1", 6379);
        
        try {
            jedis.set("hello", "world");
            jedis.get("hello");
        } catch(JedisConnectionException e) {
            log.error("couldn't connect to redis cluster");
            return null;
        }

        log.info("connected to redis cluster");
        jedis.subscribe(new RedisPubSub(), "pubsub:dev117uday");
        return jedis;
    }

}

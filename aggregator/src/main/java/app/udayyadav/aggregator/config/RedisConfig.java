package app.udayyadav.aggregator.config;

import app.udayyadav.aggregator.pubsub.RedisPubSub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;

@Configuration
public class RedisConfig {

    @Bean
    public JedisPooled getRedisConnection(){
        JedisPooled jedisPooled = new JedisPooled("127.0.0.1", 6379);
        jedisPooled.subscribe(new RedisPubSub(), "pubsub:dev117uday");
        return  jedisPooled;
    }

}

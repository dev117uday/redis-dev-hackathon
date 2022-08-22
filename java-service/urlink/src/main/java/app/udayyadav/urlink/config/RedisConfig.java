package app.udayyadav.urlink.config;

import app.udayyadav.urlink.pubsub.RedisPubSub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;

@Configuration
@Slf4j
public class RedisConfig {

    @Bean
    public JedisPooled jedisConnection() {
        JedisPooled jedisPooled = new JedisPooled("127.0.0.1", 6379);
        log.info("connection to redis established");
          return jedisPooled;
    }


}

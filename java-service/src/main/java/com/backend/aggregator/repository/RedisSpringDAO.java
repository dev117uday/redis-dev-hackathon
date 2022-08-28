package com.backend.aggregator.repository;

import com.backend.aggregator.model.ShortURL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.timeseries.DuplicatePolicy;
import redis.clients.jedis.timeseries.TSCreateParams;

import java.util.List;

@Service
@Slf4j
public class RedisSpringDAO {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    JedisPooled jedis = new JedisPooled("localhost", 6379);

    private static final String HASH_KEY = "ShortURL";

    public void save(ShortURL shortUrl) {
        redisTemplate.opsForValue().set(shortUrl.getShortUrl(), shortUrl.getLongUrl());
        String key = "links:" + shortUrl.getShortUrl();
        jedis.tsCreate(key, new TSCreateParams().duplicatePolicy(DuplicatePolicy.SUM));
        redisTemplate.opsForHash().put(HASH_KEY, shortUrl.getShortUrl(), shortUrl);
    }

    public List<Object> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Object findUrlById(String shortUrl) {
        return redisTemplate.opsForHash().get(HASH_KEY, shortUrl);
    }


}

package com.backend.aggregator.controller;

import io.github.dengliming.redismodule.redistimeseries.RedisTimeSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.timeseries.TSElement;

import java.util.List;

@RestController
@RequestMapping("api/v1/ts")
@CrossOrigin(origins = "*")
public class TSController {

    JedisPooled jedis = new JedisPooled("localhost", 6379);

    @Autowired
    private RedisTimeSeries redisTimeSeries;

    @GetMapping("{shortUrl}")
    public List<TSElement> getUrlInfo(@PathVariable String shortUrl) {

        String key = "links:" + shortUrl;

        long currentTime = System.currentTimeMillis();
        long minusTime = currentTime - ((currentTime) / 1000 / 60 / 60);

        var some = jedis.tsRange(key, minusTime, currentTime);

        return some;
    }


}

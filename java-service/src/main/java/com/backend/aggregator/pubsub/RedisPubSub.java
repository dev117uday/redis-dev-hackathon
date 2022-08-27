package com.backend.aggregator.pubsub;

import io.github.dengliming.redismodule.redistimeseries.RedisTimeSeries;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisPubSub implements MessageListener {

    @Autowired
    private RedisTimeSeries redisTimeSeries;


    @Override
    public void onMessage(CharSequence charSequence, Object o) {

    }


}

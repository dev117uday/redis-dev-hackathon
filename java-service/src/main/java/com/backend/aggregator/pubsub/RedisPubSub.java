package com.backend.aggregator.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.aggregator.model.MessagePubSub;
import com.google.gson.Gson;

import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.JedisPubSub;

@Service
public class RedisPubSub extends JedisPubSub {

    @Autowired
    private JedisPooled jedis;

    @Override
    public void onMessage(String channel, String message) {
        Gson gsonObj = new Gson();
        MessagePubSub msgFromPubSub = gsonObj.fromJson(message, MessagePubSub.class);
        jedis.tsAdd(msgFromPubSub.getShortUrl(), msgFromPubSub.getTimestamp(), 1);
    }
}

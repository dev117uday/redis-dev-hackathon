package com.backend.aggregator.pubsub;

import com.backend.aggregator.model.MessagePubSub;
import com.google.gson.Gson;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.timeseries.DuplicatePolicy;
import redis.clients.jedis.timeseries.TSCreateParams;

import java.util.HashMap;
import java.util.Map;

@Service
public class RedisPubSub implements MessageListener {

    JedisPooled jedis = new JedisPooled("localhost", 6379);

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            Gson gson = new Gson();
            MessagePubSub msgPubSub = gson.fromJson(message.toString(), MessagePubSub.class);

            Map<String, String> labels = new HashMap<>();
            labels.put("url", msgPubSub.getShortUrl());

            String key = "links:" + msgPubSub.getShortUrl();

            long and = jedis.tsAdd(key,
                    msgPubSub.getTimestamp(), 1,
                    new TSCreateParams().duplicatePolicy(DuplicatePolicy.SUM).labels(labels)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

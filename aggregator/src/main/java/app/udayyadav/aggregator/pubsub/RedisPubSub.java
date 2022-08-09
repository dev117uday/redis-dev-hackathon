package app.udayyadav.aggregator.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

@Component
public class RedisPubSub extends JedisPubSub {

    private Logger log = LoggerFactory.getLogger(RedisPubSub.class);

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        log.info("subscribed to : " + channel);
    }

    @Override
    public void onMessage(String channel, String message) {
        log.info("message : " + message + " on channel : " + channel);
    }
}

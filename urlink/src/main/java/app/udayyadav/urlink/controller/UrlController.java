package app.udayyadav.urlink.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPooled;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Slf4j
public class UrlController {

    @Autowired
    private JedisPooled jedis;

    @GetMapping("/{shortenedURL}")
    public ResponseEntity<String> redirectURL(@PathVariable("shortenedURL") String shortendurl) {

        var longURL = jedis.get(shortendurl);

        if (longURL == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("<h1>not found</h1>");
        }

        // not just url, timestamp
        jedis.publish("pubsub:dev117uday",shortendurl);

        if (!longURL.startsWith("https://") || !longURL.startsWith("http://")) {
            longURL = "http://" + longURL;
        }

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longURL)).build();
    }

    @GetMapping()
    public String redirect() {
        log.info("redirect hit");
        jedis.set("hello", "www.google.com");
        return "<h1>Hello there, looking for a shortened url ?</h1>";
    }


}

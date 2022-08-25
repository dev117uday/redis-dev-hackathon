package com.backend.aggregator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisPooled;

@RestController
@RequestMapping("agg/api/v1")
public class MainController {
    
    @Autowired
    public JedisPooled jedis;

    @GetMapping
    public ResponseEntity<String> getSimpleKey() {
        String value = jedis.get("hello");
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }
}

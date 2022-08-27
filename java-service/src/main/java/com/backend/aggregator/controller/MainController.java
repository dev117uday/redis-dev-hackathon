package com.backend.aggregator.controller;

import com.backend.aggregator.model.ShortURL;
import com.backend.aggregator.repository.RedisSpringDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/manage")
public class MainController {

    @Autowired
    private RedisSpringDAO redisSpringDAO;

    @GetMapping
    public List<Object> getSimpleKey() {
        return redisSpringDAO.findAll();
    }

    @GetMapping("/{id}")
    public Object findById(@PathVariable String id) {
        return redisSpringDAO.findUrlById(id);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ShortURL product) {
        redisSpringDAO.save(product);
        return ResponseEntity.status(201).build();
    }

}

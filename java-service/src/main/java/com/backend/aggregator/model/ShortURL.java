package com.backend.aggregator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product")
public class ShortURL implements Serializable {

    @Id
    private String shortUrl;
    private String owner;
    private List<String> tags;
    private String longUrl;
    private LocalDate createdAt = LocalDate.now();


}

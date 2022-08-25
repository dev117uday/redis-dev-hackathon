package com.backend.aggregator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePubSub {

    @JsonProperty(value = "shortUrl")
    private String shortUrl;

    @JsonProperty(value = "timestamp")
    private long timestamp;
}

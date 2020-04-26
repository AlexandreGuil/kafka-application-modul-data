package com.inti.formation.price.serillizer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class JsonPriceSeriallizer<T> implements Serializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonPriceSeriallizer() {
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null)
            return null;
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException err) {
            throw new SerializationException("Cannot sereialize the body of the instance", err);
        }
    }

    @Override
    public void close() {}
}
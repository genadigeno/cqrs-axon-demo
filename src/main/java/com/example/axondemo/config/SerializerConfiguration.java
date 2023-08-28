package com.example.axondemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SerializerConfiguration {
    @Bean
    @Primary
    public Serializer buildSerializer(ObjectMapper objectMapper) {
        return JacksonSerializer.builder().objectMapper(objectMapper).defaultTyping().build();
    }
}
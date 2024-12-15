package com.bridge.skill.notificationmanager.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MessageConverter {

    private final ObjectMapper objectMapper;

    public String serialize(final Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error while serializing object", e);
            throw new RuntimeException("Error while serializing object", e);
        }
    }

    public <T> T deserialize(final Object json, final Class<T> clazz) {

        try {
            return objectMapper.readValue(json.toString(), clazz);
        } catch (Exception e) {
            log.error("Error while deserializing object", e);
            throw new RuntimeException("Error while deserializing object", e);
        }
    }
}

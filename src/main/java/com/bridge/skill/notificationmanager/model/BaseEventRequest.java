package com.bridge.skill.notificationmanager.model;

import com.bridge.skill.notificationmanager.constants.EventType;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class BaseEventRequest {

    protected BaseEventRequest(final EventType eventType) {

        this.eventId = UUID.randomUUID().getMostSignificantBits();
        this.timeStamp = LocalDateTime.now();
        this.eventType = eventType;
    }

    private long eventId;
    private LocalDateTime timeStamp;
    private EventType eventType;
}

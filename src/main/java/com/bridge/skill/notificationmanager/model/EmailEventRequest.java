package com.bridge.skill.notificationmanager.model;

import com.bridge.skill.notificationmanager.constants.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmailEventRequest extends BaseEventRequest {

    private EmailEventRequest() {
        super(null);
    }

    public EmailEventRequest(final String emailTo , final EventType eventType) {
        super(eventType);
        this.emailTo = emailTo;
    }

    private String emailTo;
}

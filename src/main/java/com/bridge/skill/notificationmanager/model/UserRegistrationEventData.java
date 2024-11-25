package com.bridge.skill.notificationmanager.model;

import com.bridge.skill.notificationmanager.constants.UserType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class UserRegistrationEventData implements Serializable {

    private String userId;
    private UserType userType;
    private String email;
    private String countryCode;
    private String mobileNumber;
}

package com.bridge.skill.notificationmanager.model;

import com.bridge.skill.notificationmanager.constants.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationEventRequest implements Serializable {

    private String userId;
    private UserType userType;
    private String userName;
    private String email;
    private String countryCode;
    private String mobileNumber;
}


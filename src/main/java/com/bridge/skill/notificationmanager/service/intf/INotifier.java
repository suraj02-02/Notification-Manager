package com.bridge.skill.notificationmanager.service.intf;

import com.bridge.skill.notificationmanager.model.BaseEventRequest;
import reactor.core.publisher.Mono;

public interface INotifier {

    Mono<Void> notify(final BaseEventRequest baseEventRequest);
}

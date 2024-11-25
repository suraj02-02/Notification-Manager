package com.bridge.skill.notificationmanager.consumer;

import reactor.core.publisher.Mono;

public interface BaseEventConsumer {

    /**
     * Method is used to consume event from cable
     *
     * @param message
     */
    Mono<Void> consumeEvent(final Mono<Object> message);
}

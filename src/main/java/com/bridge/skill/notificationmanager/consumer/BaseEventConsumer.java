package com.bridge.skill.notificationmanager.consumer;

import reactor.core.publisher.Mono;

/**
 * <p>
 *     This is the base interface for any kind of event to be consumed by <code>Notification-Manager</code>.
 *     All the events that needs to be consumed by <code>Notification-Manager</code> should implement this interface
 * </p>
 *
 * @author surajyadav
 */
public interface BaseEventConsumer {

    /**
     * Method is used to consume event from cable
     * @param message Message payload that needs to be consumed
     */
    Mono<Void> consumeEvent(final Object message);
}

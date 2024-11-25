package com.bridge.skill.notificationmanager.consumer;

import com.bridge.skill.notificationmanager.model.UserRegistrationEventData;
import com.bridge.skill.notificationmanager.service.INotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * <code>UserRegistrationConsumer</code> - Kafka consumer for user registration events
 */

@Slf4j
@Component
public class UserRegistrationConsumer implements BaseEventConsumer {

    private final INotifier emailNotifier;

    public UserRegistrationConsumer(@Qualifier("mailSendServiceProvider") final INotifier emailNotifier) {
        this.emailNotifier = emailNotifier;
    }


    @Override
    @KafkaListener(topics = "${cable.event-types.USER_REGISTRATION_EVENT}", groupId = "${spring.kafka.consumer.group-id}")
    public Mono<Void> consumeEvent(final Mono<Object> message) {

        return message
                .doOnNext(obj -> log.info("Received message: {}", obj))
                .map(obj -> (UserRegistrationEventData) obj)
                .flatMap(userRegistrationEventData -> this.emailNotifier.notify(userRegistrationEventData))
                .doOnError(error -> log.error("Error occurred while processing event: {}", error.getMessage()))
                .then();
    }

}

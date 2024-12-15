package com.bridge.skill.notificationmanager.repository;

import com.bridge.skill.notificationmanager.constants.EventType;
import com.bridge.skill.notificationmanager.entity.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface NotificationEventRepository extends ReactiveMongoRepository<Event, String> {

    Mono<Event> findEventByEventType(EventType eventType);
}

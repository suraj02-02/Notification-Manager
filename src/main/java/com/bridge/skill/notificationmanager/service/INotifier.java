package com.bridge.skill.notificationmanager.service;

import reactor.core.publisher.Mono;

public interface INotifier {

    Mono<Void> notify(final Object message);
}

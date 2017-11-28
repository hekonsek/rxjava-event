package com.github.hekonsek.rxjava.event;

import java.util.Map;

import static java.util.Collections.emptyMap;

public final class Events {

    private Events() {
    }

    static <T> Event<T> event(Map<String, Object> headers, T payload) {
        return new Event<>(headers, payload);
    }

    static <T> Event<T> event(T payload) {
        return event(emptyMap(), payload);
    }

}

package com.github.hekonsek.rxjava.event;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class Event<T> {

    private final Map<String, Object> headers;

    private final T payload;

    public Event(Map<String, Object> headers, T payload) {
        this.headers = ImmutableMap.copyOf(headers);
        this.payload = payload;
    }

    public Map<String, Object> headers() {
        return headers;
    }

    public T payload() {
        return payload;
    }

}
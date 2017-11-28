package com.github.hekonsek.rxjava.event;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static com.github.hekonsek.rxjava.event.Events.event;
import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {

    @Test
    public void shouldCreateEventWithPayloadOnly() {
        Event<String> event = event("foo");
        assertThat(event.payload()).isEqualTo("foo");
    }

    @Test
    public void shouldCreateEventWithPayloadAndHeaders() {
        Event<String> event = event(ImmutableMap.of("foo", "bar"), "baz");
        assertThat(event.payload()).isEqualTo("baz");
        assertThat(event.headers()).containsEntry("foo", "bar");
    }

}

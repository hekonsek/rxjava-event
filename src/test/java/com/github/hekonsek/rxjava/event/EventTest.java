/**
 * Licensed to the RxJava Event under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.hekonsek.rxjava.event;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Date;

import static com.github.hekonsek.rxjava.event.Events.event;
import static com.github.hekonsek.rxjava.event.Headers.ADDRESS;
import static com.github.hekonsek.rxjava.event.Headers.KEY;
import static com.github.hekonsek.rxjava.event.Headers.ORIGINAL;
import static com.github.hekonsek.rxjava.event.Headers.REPLY_CALLBACK;
import static com.github.hekonsek.rxjava.event.Headers.original;
import static com.github.hekonsek.rxjava.event.Headers.replyHandler;
import static com.github.hekonsek.rxjava.event.Headers.requiredAddress;
import static com.github.hekonsek.rxjava.event.Headers.requiredKey;
import static com.github.hekonsek.rxjava.event.Headers.requiredOriginal;
import static com.github.hekonsek.rxjava.event.Headers.requiredReplyHandler;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class EventTest {

    String originalEvent = randomUUID().toString();

    String payload = randomUUID().toString();

    Event<String> event = event(ImmutableMap.of(ORIGINAL, originalEvent), payload);

    @Test
    public void shouldCopyWithNewPayload() {
        Event<String> newEvent = event.withPayload("new");
        assertThat(newEvent.payload()).isEqualTo("new");
    }

    @Test
    public void shouldCopyAndAddHeader() {
        Event<String> newEvent = event.withHeader("header", "headerValue");
        assertThat(newEvent.headers()).containsEntry("header", "headerValue");
    }

    @Test
    public void shouldGetOriginalEvent() {
        assertThat(requiredOriginal(event, String.class)).isEqualTo(originalEvent);
    }

    @Test
    public void shouldValidateInvalidOriginalEvent() {
        Throwable ex = catchThrowable(() -> original(event, Date.class));
        assertThat(ex).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("event is not of type");
    }

    @Test
    public void shouldCreateEventWithPayload() {
        assertThat(event.payload()).isEqualTo(payload);
    }

    @Test
    public void shouldCreateEventWithPayloadAndHeaders() {
        Event<String> event = event(ImmutableMap.of("foo", "bar"), "baz");
        assertThat(event.payload()).isEqualTo("baz");
        assertThat(event.headers()).containsEntry("foo", "bar");
    }

    @Test
    public void shouldCreateEventWithKey() {
        Event<String> event = event(ImmutableMap.of(KEY, "foo"), null);
        assertThat(requiredKey(event)).isEqualTo("foo");
    }

    @Test
    public void shouldCreateEventWithAddress() {
        Event<String> event = event(ImmutableMap.of(ADDRESS, "foo"), null);
        assertThat(requiredAddress(event)).isEqualTo("foo");
    }

    @Test
    public void shouldSendReply() {
        TestReplyHandler callback = new TestReplyHandler();
        Event<String> event = event(ImmutableMap.of(REPLY_CALLBACK, callback), null);
        requiredReplyHandler(event).reply("response");
        assertThat(callback.response).isEqualTo("response");
    }

    @Test
    public void shouldCompleteReply() {
        TestReplyHandler callback = new TestReplyHandler();
        Event<String> event = event(ImmutableMap.of(REPLY_CALLBACK, callback), null);
        replyHandler(event).get().reply("response").blockingAwait();
    }

    @Test
    public void shouldCompleteAsyncReply() {
        AsyncTestReplyHandler callback = new AsyncTestReplyHandler();
        Event<String> event = event(ImmutableMap.of(REPLY_CALLBACK, callback), null);
        replyHandler(event).get().reply("response").blockingAwait();
    }

    @Test
    public void shouldIncludePayloadInToString() {
        Event<String> event = event("baz");
        assertThat(event.toString()).contains("payload=baz");
    }

    @Test
    public void shouldIncludeHeadersInToString() {
        Event<String> event = event(ImmutableMap.of("foo", "bar"), null);
        assertThat(event.toString()).contains("headers={foo=bar}");
    }

}
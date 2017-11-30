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
import static com.github.hekonsek.rxjava.event.Headers.address;
import static com.github.hekonsek.rxjava.event.Headers.key;
import static com.github.hekonsek.rxjava.event.Headers.original;
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

    @Test
    public void shouldCreateEventWithKey() {
        Event<String> event = event(ImmutableMap.of(KEY, "foo"), null);
        assertThat(key(event)).isEqualTo("foo");
    }

    @Test
    public void shouldCreateEventWithAddress() {
        Event<String> event = event(ImmutableMap.of(ADDRESS, "foo"), null);
        assertThat(address(event)).isEqualTo("foo");
    }

    @Test
    public void shouldGetOriginalEvent() {
        Date originalEvent = new Date();
        Event<String> event = event(ImmutableMap.of(ORIGINAL, originalEvent), null);
        assertThat(original(event, Date.class)).isEqualTo(originalEvent);
    }

}
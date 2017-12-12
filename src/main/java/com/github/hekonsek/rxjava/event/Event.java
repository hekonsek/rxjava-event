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

import java.util.Map;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Class representing event. It can be used to carry information about event body (payload) and its
 * metadata (headers).

 * @param <T> Payload type.
 */
public class Event<T> {

    // Members

    private final Map<String, Object> headers;

    private final T payload;

    // Constructors

    public Event(Map<String, Object> headers, T payload) {
        this.headers = ImmutableMap.copyOf(headers);
        this.payload = payload;
    }

    // Accessors

    public Map<String, Object> headers() {
        return headers;
    }

    public T payload() {
        return payload;
    }

    // Copy-mutators

    public Event<T> withPayload(T payload) {
        return new Event<>(headers, payload);
    }

    public Event<T> withHeader(String key, Object value) {
        Map<String, Object> copiedHeaders = ImmutableMap.<String, Object>builder().putAll(this.headers).put(key, value).build();
        return new Event<>(copiedHeaders, payload);
    }

    // Object overriddens

    @Override public String toString() {
        return toStringHelper(this).
                add("headers", headers).
                add("payload", payload).
                toString();
    }

}
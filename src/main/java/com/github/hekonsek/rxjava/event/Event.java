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

/**
 * Class representing event. It can be used to carry information about event body (payload) and its
 * metadata (headers).

 * @param <T> Payload type.
 */
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

    public Event<T> withHeader(String key, Object value) {
        Map<String, Object> headers = ImmutableMap.<String, Object>builder().putAll(this.headers).put(key, value).build();
        return new Event<>(headers, payload);
    }

    public T payload() {
        return payload;
    }

    public Event<T> withPayload(T payload) {
        return new Event<>(headers, payload);
    }

}
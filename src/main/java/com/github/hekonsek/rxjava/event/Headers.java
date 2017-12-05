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

import java.util.Optional;

import static java.util.Optional.ofNullable;

public final class Headers {

    private Headers() {
    }

    public static final String ORIGINAL = "rxjava.event.original";

    public static final String ADDRESS = "rxjava.event.address";

    public static final String KEY = "rxjava.event.key";

    public static final String RESPONSE_CALLBACK = "rxjava.event.response.callback";

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> original(Event event, Class<T> type) {
        Object originalEvent = event.headers().get(ORIGINAL);
        if(!type.isAssignableFrom(originalEvent.getClass())) {
            throw new IllegalArgumentException("Original event is not of type: " + type.getName());
        }
        return ofNullable((T) originalEvent);
    }

    public static String address(Event event) {
        return (String) event.headers().get(ADDRESS);
    }

    public static String key(Event event) {
        return (String) event.headers().get(KEY);
    }

    public static <R> R responseCallback(Event event, Class<? extends ResponseCallback> type) {
        ResponseCallback callback = (ResponseCallback) event.headers().get(RESPONSE_CALLBACK);
        return (R) callback;
    }

}

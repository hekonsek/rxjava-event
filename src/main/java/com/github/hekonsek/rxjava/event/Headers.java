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

    // Constants

    public static final String ORIGINAL = "rxjava.event.original";

    public static final String ADDRESS = "rxjava.event.address";

    public static final String KEY = "rxjava.event.key";

    public static final String REPLY_CALLBACK = "rxjava.event.reply.callback";

    // Constructors

    private Headers() {
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> original(Event event, Class<T> type) {
        Object originalEvent = event.headers().get(ORIGINAL);
        if(!type.isAssignableFrom(originalEvent.getClass())) {
            throw new IllegalArgumentException("Original event is not of type: " + type.getName());
        }
        return ofNullable((T) originalEvent);
    }

    public static <T> T requiredOriginal(Event event, Class<T> type) {
        return original(event, type).orElseThrow(() -> new IllegalArgumentException("Original event header not found."));
    }

    public static Optional<String> address(Event event) {
        return ofNullable((String) event.headers().get(ADDRESS));
    }

    public static String requiredAddress(Event event) {
        return address(event).orElseThrow(() -> new IllegalArgumentException("Address header not found."));
    }


    public static Optional<String> key(Event event) {
        return ofNullable((String) event.headers().get(KEY));
    }

    public static String requiredKey(Event event) {
        return key(event).orElseThrow(() -> new IllegalArgumentException("Key header not found."));
    }


    public static Optional<ReplyHandler> replyHandler(Event event) {
        return ofNullable((ReplyHandler) event.headers().get(REPLY_CALLBACK));
    }

    public static ReplyHandler requiredReplyHandler(Event event) {
        return replyHandler(event).orElseThrow(() -> new IllegalArgumentException("No reply handler found."));
    }

}

# RxJava Event

[![Version](https://img.shields.io/badge/RxJava%20Event-0.6-blue.svg)](https://github.com/hekonsek/rxjava-event/releases)
[![Build](https://api.travis-ci.org/hekonsek/rxjava-event.svg)](https://travis-ci.org/hekonsek/rxjava-event)
[![Coverage](https://sonarcloud.io/api/badges/measure?key=com.github.hekonsek%3Arxjava-event&metric=coverage)](https://sonarcloud.io/component_measures?id=com.github.hekonsek%3Arxjava-event&metric=coverage)

RxJava Event library provides simple event model for messaging and data oriented RxJava applications.

## Installation

In order to start using RxJava Event add the following dependency to your Maven project:

    <dependency>
      <groupId>com.github.hekonsek</groupId>
      <artifactId>rxjava-event</artifactId>
      <version>0.6</version>
    </dependency>

## Usage

RxJava Events provides simple event class that can be used to carry information about event body (`payload`) and its metadata (`headers`).

Event payload is the primary piece of data carried by an event. Here is how you can create event with a payload:

```
import com.github.hekonsek.rxjava.event.Event;
import static com.github.hekonsek.rxjava.event.Events.event;

...

Event<String> event = event("myPayload");
assertThat(event.payload()).isEqualTo("myPayload");
```

Event headers is a map describing event metadata.

```
import com.github.hekonsek.rxjava.event.Event;
import com.google.common.collect.ImmutableMap;
import static com.github.hekonsek.rxjava.event.Events.event;

...

Map<String, Object> headers = ImmutableMap.of("myHeader", "someValue");
Event<String> eventWithHeaders = event("myPayload", headers);
assertThat(eventWithHeaders.headers()).isEqualTo(headers);
```

While headers are represented as arbitrary `Map<String, Object` object, RxJava Event provides convention and helper methods to 
access common metadata:

### Original event header

**Original event** header holds an original object that has been used to create `Event` instance. This can be Apache Kafka record,
Vert.x HTTP request object, JMS API message and so forth. This header is optional.

```
import java.util.Date;

import static com.github.hekonsek.rxjava.event.Headers.ORIGINAL;
import static com.github.hekonsek.rxjava.event.Headers.original;

...

Date theOriginalEvent = new Date();
Event<String> event = event(originalEvent.getTime(), ImmutableMap.of(ORIGINAL, theOriginalEvent));
Date originalEvent = original(event, Date.class);
```

### Address header

**Address** header represents name of the channel from which an event originated. This can be Apache Kafka topic name, AMQP address,
HTTP request URI and so forth. This header is optional.

```
import static com.github.hekonsek.rxjava.event.Headers.ADDRESS;
import static com.github.hekonsek.rxjava.event.Headers.address;

...

Event<String> event = event("payload body", ImmutableMap.of(ADDRESS, "from"));
String address = address(event);
```

### Key header

**Key** header represents identifier of a payload in a form of `String`. It is useful for representing entities in stream of events, in particular
for event sourcing scenarios. This header is optional.

For example you can associate events to certain people using their name as identifier:

```
import static com.github.hekonsek.rxjava.event.Headers.KEY;

...

Map<String, Object> johnHeaders = ImmutableMap.of(KEY, "john");
int johnAge = 30;
Event<String> eventWithHeaders = event(johnAge, johnHeaders);

Map<String, Object> fredHeaders = ImmutableMap.of(KEY, "fred");
int fredAge = 30;
Event<String> eventWithHeaders = event(fredAge, fredHeaders);

```

## License

This project is distributed under Apache 2.0 license.
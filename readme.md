# RxJava Event

[![Version](https://img.shields.io/badge/RxJava%20Event-0.1-blue.svg)](https://github.com/hekonsek/rxjava-event/releases)
[![Build](https://api.travis-ci.org/hekonsek/rxjava-event.svg)](https://travis-ci.org/hekonsek/rxjava-event)

RxJava Event library provides simple event model for messaging and data oriented RxJava applications.

## Installation

In order to start using RxJava Event add the following dependency to your Maven project:

    <dependency>
      <groupId>com.github.hekonsek</groupId>
      <artifactId>rxjava-event</artifactId>
      <version>0.1</version>
    </dependency>

## Usage

RxJava Events provides simple event class that can be used to carry information about event body (`payload`) and its metadata (`headers`).

```
import static com.github.hekonsek.rxjava.event.Events.event;

...

Event<String> event = event("myPayload");
assertThat(event.payload()).isEqualTo("myPayload");

Map<String, Object> headers = ImmutableMap.of("myHeader", "someValue");
Event<String> eventWithHeaders = event("myPayload", headers);
assertThat(eventWithHeaders.headers()).isEqualTo(headers);

```

## License

This project is distributed under Apache 2.0 license.
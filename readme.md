# RxJava Event

[![Version](https://img.shields.io/badge/RxJava%20Event-0.0-blue.svg)](https://github.com/hekonsek/rxjava-event/releases)
[![Build](https://api.travis-ci.org/hekonsek/rxjava-event.svg)](https://travis-ci.org/hekonsek/rxjava-event)

RxJava Event library provides simple event model for messaging and data oriented RxJava applications.

## Installation

In order to start using RxJava Event add the following dependency to your Maven project:

    <dependency>
      <groupId>com.github.hekonsek</groupId>
      <artifactId>rxjava-event</artifactId>
      <version>0.0</version>
    </dependency>

## Usage

In order to register echo function which just copies incoming event from Kafka topic `source` to topic `target`,
create an appropriate `Pipe` definition and start it using `Pipes` instance:

```
import io.vertx.core.Vertx;
import com.github.hekonsek.vertx.pipes.Pipe;
import com.github.hekonsek.vertx.pipes.Pipes;
import com.github.hekonsek.vertx.pipes.SimpleFunctionRegistry;

import static io.vertx.core.Vertx.vertx;
import static com.github.hekonsek.vertx.pipes.Pipe.pipe;
import static com.github.hekonsek.vertx.pipes.Pipes.pipes;
...

Vertx vertx = vertx();

SimpleFunctionRegistry functionRegistry = new SimpleFunctionRegistry();
functionRegistry.registerFunction("echoFunction", event -> event.reply(event.body()));

Pipes pipes = pipes(vertx, functionRegistry);

pipes.startPipe(pipe("myFunctionPipe", "sourceTopic", "echoFunction", "targetTopic"));
```

## License

This project is distributed under Apache 2.0 license.
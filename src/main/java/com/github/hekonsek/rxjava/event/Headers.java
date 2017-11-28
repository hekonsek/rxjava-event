package com.github.hekonsek.rxjava.event;

public class Headers {

    public static final String KEY = "rxjava.event.key";

    public static final String ADDRESS = "rxjava.event.address";

    public static String key(Event event) {
        return (String) event.headers().get(KEY);
    }

    public static String address(Event event) {
        return (String) event.headers().get(ADDRESS);
    }

}

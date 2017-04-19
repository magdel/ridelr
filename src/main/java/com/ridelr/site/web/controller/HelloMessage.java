package com.ridelr.site.web.controller;

/**
 * Created by rfk on 16.03.2016.
 */
public class HelloMessage {

    private String name;

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "HelloMessage{" +
                "name='" + name + '\'' +
                '}';
    }
}
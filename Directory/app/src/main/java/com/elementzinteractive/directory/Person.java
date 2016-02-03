package com.elementzinteractive.directory;

/**
 * Created by Elementz on 2/3/2016.
 */
public class Person {
    private static Person ourInstance = new Person();

    public static Person getInstance() {
        return ourInstance;
    }

    private Person() {
    }
}

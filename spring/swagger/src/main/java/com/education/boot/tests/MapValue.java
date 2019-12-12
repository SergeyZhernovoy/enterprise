package com.education.boot.tests;

public class MapValue {
    private String name;

    public MapValue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MapValue{" +
                "name='" + name + '\'' +
                '}';
    }
}
